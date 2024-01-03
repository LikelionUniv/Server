package likelion.univ.domain.like.postlike.service;

import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
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

    public boolean createOrDeletePostLike(PostLikeCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();
        Post post = postAdaptor.findById(postId);
        User user = userAdaptor.findById(loginUserId);

        if (existsPostLike(postId, loginUserId)) {
            PostLike postLike = postLikeAdaptor.findByPostAndUser(post, user);
            if (isAuthorized(postLike, loginUserId)) {
                postLikeAdaptor.delete(postLike);
                return false;
            }
            throw new NotAuthorizedException();
        }
        PostLike newPostLike = newPostLikeBy(request);
        postLikeAdaptor.save(newPostLike);
        return true;
    }

    private Boolean existsPostLike(Long postId, Long loginUserId) {
        return postLikeAdaptor.existsByPostIdAndAuthorId(postId, loginUserId);
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
