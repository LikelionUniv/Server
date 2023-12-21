package likelion.univ.domain.like.postlike.service;

import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
import likelion.univ.domain.like.postlike.dto.PostLikeDeleteServiceDto;
import likelion.univ.domain.like.postlike.dto.PostLikeResponseDto;
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

    public void createOrDeletePostLike(PostLikeCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();
        Post post = postAdaptor.findById(postId);
        User user = userAdaptor.findById(loginUserId);
        if (postLikeAdaptor.existsByPostIdAndAuthorId(postId, loginUserId)) {
            PostLike postLike = postLikeAdaptor.findByPostAndUser(post, user);
            if (isAuthorized(postLike, loginUserId)) {
                postLikeAdaptor.delete(postLike);
                return;
            }
            throw new NotAuthorizedException();
        }
        PostLike newPostLike = newPostLikeBy(request);
        postLikeAdaptor.save(newPostLike);
    }



    private boolean isAuthorized(PostLike findPostLike, Long loginUserId) {
        Long authorId = findPostLike.getUser().getId();
        return loginUserId.equals(authorId);
    }

    private PostLike newPostLikeBy(PostLikeCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();
        Post findPost = postAdaptor.findById(postId);
        User findUser = userAdaptor.findById(loginUserId);
        return PostLike.builder()
                .post(findPost)
                .user(findUser)
                .build();
    }


}
