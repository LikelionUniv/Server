package likelion.univ.user.usecase;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.like.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetPostsCommentedByMeUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final PostRepository postRepository;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;
    private final PostLikeRepository postLikeRepository;

    public PageResponse<UserPagePostsDto> execute(Long userId, Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postRepository.findByCommentAuthorId(userId, pageable);

        List<Long> postIds = posts.get().map(p -> p.getId()).collect(Collectors.toList());
        List<Long> myLikedPostIds = postLikeRepository.findPostIdsByUserIdAndPostIdsIn(currentUserId, postIds);

        return PageResponse.of(posts.map(p -> UserPagePostsDto.of(p, currentUserId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()),
                myLikedPostIds.contains(p.getId()))));
    }
}
