package likelion.univ.adminPost.usecase;

import java.util.Optional;
import likelion.univ.adminPost.dto.response.PostInfoResponseDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.comment.repository.CommentRepository;
import likelion.univ.domain.like.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetPostsByCategoriesAndUniversityUseCase {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final PostCountInfoRedisDao postCountInfoRedisDao;
    private final PostCountInfoRedisService postCountInfoRedisService;

    private final AuthenticatedUserUtils authenticatedUserUtils;

    public PageResponse<PostInfoResponseDto> execute(
            Pageable pageable,
            MainCategory mainCategory,
            SubCategory subCategory
    ) {
        User user = authenticatedUserUtils.getCurrentUser();
        Page<Post> posts = postRepository.findByCategoriesAndUniversityOrderByCreatedDate
                (mainCategory, subCategory, user.getUniversityInfo().getUniversity().getId(), pageable);
        return PageResponse.of(posts.map(this::createResult));
    }

    private PostInfoResponseDto createResult(Post post) {
        PostCountInfo postCountInfo = getPostCountInfo(post.getId());
        return PostInfoResponseDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount());
    }

    private PostCountInfo getPostCountInfo(Long postId) {
        Optional<PostCountInfo> postCountInfo = postCountInfoRedisDao.findById(postId);
        if (postCountInfo.isEmpty()) {
            Long commentCount = commentRepository.countByPostId(postId);
            Long likeCount = postLikeRepository.countByPostId(postId);
            return postCountInfoRedisService.save(postId, commentCount, likeCount);
        }
        return postCountInfo.get();
    }
}
