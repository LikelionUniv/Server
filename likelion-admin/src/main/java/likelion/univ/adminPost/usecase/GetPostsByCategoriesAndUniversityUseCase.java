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
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetPostsByCategoriesAndUniversityUseCase {
    private final PostAdaptor postAdaptor;
    private final PostLikeAdaptor postLikeAdaptor;
    private final CommentAdaptor commentAdaptor;
    private final AuthenticatedUserUtils authentiatedUserUtils;
    public PageResponse<PostInfoResponseDto> execute(Pageable pageable, MainCategory mainCategory, SubCategory subCategory){
        User user = authentiatedUserUtils.getCurrentUser();

        Page<Post> posts = postAdaptor.findPostsByCategoriesAndUniversityOrderByCreatedDate
                (mainCategory, subCategory, user.getUniversityInfo().getUniversity().getId(), pageable);

        return PageResponse.of(posts.map(post -> PostInfoResponseDto.of(post, post.getPostLikes().size(), post.getComments().size())));
    }

}