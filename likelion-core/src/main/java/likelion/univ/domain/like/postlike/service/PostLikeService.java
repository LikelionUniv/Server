package likelion.univ.domain.like.postlike.service;

import likelion.univ.domain.comment.exception.NotAuthorizedException;
import likelion.univ.domain.like.postlike.dto.PostLikeCommand;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.like.postlike.repository.PostLikeRepository;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public boolean createOrDeletePostLike(PostLikeCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();
        Post post = postRepository.getById(postId);
        User user = userRepository.getById(loginUserId);

        if (existsPostLike(postId, loginUserId)) {
            PostLike postLike = postLikeRepository.getByPostAndUser(post, user);
            if (isAuthorized(postLike, loginUserId)) {
                postLikeRepository.delete(postLike);
                return false;
            }
            throw new NotAuthorizedException();
        }
        PostLike newPostLike = newPostLikeBy(request);
        postLikeRepository.save(newPostLike);
        return true;
    }

    private Boolean existsPostLike(Long postId, Long loginUserId) {
        return postLikeRepository.existsByPostIdAndUserId(postId, loginUserId);
    }

    private boolean isAuthorized(PostLike findPostLike, Long loginUserId) {
        Long authorId = findPostLike.getUser().getId();
        return loginUserId.equals(authorId);
    }

    private PostLike newPostLikeBy(PostLikeCommand request) {
        Long postId = request.postId();
        Long loginUserId = request.loginUserId();
        Post findPost = postRepository.getById(postId);
        User findUser = userRepository.getById(loginUserId);
        return PostLike.builder()
                .post(findPost)
                .user(findUser)
                .build();
    }
}
