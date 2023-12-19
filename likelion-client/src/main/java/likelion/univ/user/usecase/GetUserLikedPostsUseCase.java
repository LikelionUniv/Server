package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetUserLikedPostsUseCase {
    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final PostAdaptor postAdaptor;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<UserPagePostsDto> execute(Long userId, Pageable pageable, String sort, String search){
        Long currentUserId = authentiatedUserUtils.getCurrentUserId();
        Page<Post> posts = postAdaptor.findByPostLikeAuthorId(userId, pageable, sort, search);

        return PageResponse.of(posts.map(p-> UserPagePostsDto.of(p, currentUserId, getOrCreatePostCountInfoProcessor.execute(p.getId()))));
    }

}