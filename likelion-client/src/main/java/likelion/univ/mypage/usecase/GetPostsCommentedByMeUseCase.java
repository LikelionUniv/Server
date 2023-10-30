package likelion.univ.mypage.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.page.PageResponse;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.post.adaptor.LikePostAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.mypage.dto.response.MyPagePostsDto;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetPostsCommentedByMeUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final PostAdaptor postAdaptor;
    private final PostCountInfoRedisDao postCountInfoRedisDao;
    private final PostCountInfoRedisService postCountInfoRedisService;
    private final CommentAdaptor commentAdaptor;
    private final LikePostAdaptor likePostAdaptor;

    public PageResponse<MyPagePostsDto> execute(Pageable pageable){
        Long userId = authentiatedUserUtils.getCurrentUserId();
        Page<Post> posts = postAdaptor.findByCommentAuthorId(userId, pageable);

        return PageResponse.of(posts.map(p-> createDto(p, userId)));
    }

    private MyPagePostsDto createDto(Post post, Long userId){
        PostCountInfo postCountInfo = getPostCountInfo(post.getId());
        if(post.getAuthor().getId().equals(userId))
            return MyPagePostsDto.withAuthorOf(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),true);
        else return MyPagePostsDto.withAuthorOf(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),false);
    }

    private PostCountInfo getPostCountInfo(Long postId){
        Optional<PostCountInfo> postCountInfo = postCountInfoRedisDao.findById(postId);
        if(postCountInfo.isEmpty()){
            Long commentCount = commentAdaptor.countByPostId(postId);
            Long likeCount = likePostAdaptor.countByPostId(postId);
            return postCountInfoRedisService.save(postId, commentCount, likeCount);
        }else return postCountInfo.get();
    }
}
