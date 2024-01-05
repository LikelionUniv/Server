package likelion.univ.adminPost.usecase;

import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GetPostsByCategoriesAndUniversityUseCase {
    private final PostAdaptor postAdaptor;
    private final PostLikeAdaptor postLikeAdaptor;
    private final CommentAdaptor commentAdaptor;
    private final PostCountInfoRedisDao postCountInfoRedisDao;
    private final PostCountInfoRedisService postCountInfoRedisService;

    private final AuthenticatedUserUtils authentiatedUserUtils;
    public PageResponse<PostInfoResponseDto> execute(Pageable pageable, MainCategory mainCategory, SubCategory subCategory){
        User user = authentiatedUserUtils.getCurrentUser();

        Page<Post> posts = postAdaptor.findPostsByCategoriesAndUniversityOrderByCreatedDate
                (mainCategory, subCategory, user.getUniversityInfo().getUniversity().getId(), pageable);

        return PageResponse.of(posts.map(post -> createResult(post)));
    }

    private PostInfoResponseDto createResult(Post post){
        PostCountInfo postCountInfo = getPostCountInfo(post.getId());
        return PostInfoResponseDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount());
    }
    private PostCountInfo getPostCountInfo(Long postId){
        Optional<PostCountInfo> postCountInfo = postCountInfoRedisDao.findById(postId);
        if(postCountInfo.isEmpty()){
            Long commentCount = commentAdaptor.countByPostId(postId);
            Long likeCount = postLikeAdaptor.countByPostId(postId);
            return postCountInfoRedisService.save(postId, commentCount, likeCount);
        }else return postCountInfo.get();
    }

}