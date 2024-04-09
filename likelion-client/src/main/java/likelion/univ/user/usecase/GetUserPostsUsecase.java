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
public class GetUserPostsUsecase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<UserPagePostsDto> execute(Long userId, Pageable pageable) {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postRepository.findAllByAuthorId(userId, pageable);

        List<Long> postIds = posts.get().map(p -> p.getId()).collect(Collectors.toList());
        List<Long> myLikedPostIds = postLikeRepository.findPostIdsByUserIdAndPostIdsIn(currentUserId, postIds);

        return PageResponse.of(posts.map(p -> UserPagePostsDto.of(p, currentUserId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()),
                myLikedPostIds.contains(p.getId()))));
    }
}
