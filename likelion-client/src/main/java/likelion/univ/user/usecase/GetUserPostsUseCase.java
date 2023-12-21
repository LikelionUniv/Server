package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.post.processor.GetOrCreatePostCountInfoProcessor;
import likelion.univ.user.dto.response.UserPagePostsDto;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@UseCase
@RequiredArgsConstructor
public class GetUserPostsUseCase {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final PostAdaptor postAdaptor;
    private final GetOrCreatePostCountInfoProcessor getOrCreatePostCountInfoProcessor;

    public PageResponse<UserPagePostsDto> execute(Long userId, Pageable pageable){
        Long currentUserIdId = authenticatedUserUtils.getCurrentUserId();
        Page<Post> posts = postAdaptor.findAllByAuthor_Id(userId, pageable);
        return PageResponse.of(posts.map(p-> UserPagePostsDto.of(p, currentUserIdId,
                getOrCreatePostCountInfoProcessor.execute(p.getId()))));
    }
}
