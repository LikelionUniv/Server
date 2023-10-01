package likelion.univ.domain.postlike.service;

import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.postlike.dto.PostLikeCreateServiceDto;
import likelion.univ.domain.postlike.dto.PostLikeDeleteServiceDto;
import likelion.univ.domain.postlike.dto.PostLikeResponseDto;
import likelion.univ.domain.postlike.entity.PostLike;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeDomainService {

    private final PostLikeAdaptor postLikeAdaptor;
    private final PostAdaptor postAdaptor;
    private final UserAdaptor userAdaptor;

    public PostLikeResponseDto createLikePost(PostLikeCreateServiceDto request) {
        PostLike newPostLike = newPostLikeBy(request);
        Long savedLikeId = postLikeAdaptor.save(newPostLike);

        return PostLikeResponseDto.builder()
                .id(savedLikeId)
                .build();
    }


    public void deleteLikePost(PostLikeDeleteServiceDto request) {
        Long postLikeId = request.getPostLikeId();
        Long loginUserId = request.getLoginUserId();
        PostLike findPostLike = postLikeAdaptor.findById(postLikeId);

        if (isAuthorized(findPostLike, loginUserId)) {
            postLikeAdaptor.delete(findPostLike);
        }
        throw new NotAuthorizedException();
    }

    private boolean isAuthorized(PostLike findPostLike, Long loginUserId) {
        Long authorId = findPostLike.getAuthor().getId();
        return loginUserId.equals(authorId);
    }

    private PostLike newPostLikeBy(PostLikeCreateServiceDto request) {
        Long postId = request.getPostId();
        Long authorId = request.getAuthorId();
        Post findPost = postAdaptor.findById(postId);
        User findUser = userAdaptor.findById(authorId);
        PostLike newPostLike = PostLike.builder()
                .post(findPost)
                .author(findUser)
                .build();
        return newPostLike;
    }


}
