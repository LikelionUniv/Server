package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.entity.Comment;
import likelion.univ.domain.entity.Post;
import likelion.univ.domain.entity.User;
import likelion.univ.domain.repository.CommentRepository;
import likelion.univ.domain.repository.PostRepository;
import likelion.univ.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDto.ResponseSave createParentComment(CommentDto.RequestSave createRequest, Long postId, Long userId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no such post"));
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no such user"));

        return createCommentAndSave(createRequest, findPost, findUser);
    }

    @Override
    public CommentDto.ResponseSave createChildComment(CommentDto.RequestSave createRequest, Long postId, Long userId, Long parentId) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("no such post"));
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("no such user"));
        Comment findComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new NoSuchElementException("no such comment"));

        return createChildCommentAndSave(createRequest, findPost, findUser, findComment);
    }



    @Override
    public CommentDto.ResponseSave updateCommentBody(CommentDto.RequestSave updateRequest, Long commentId, Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("no such user"));
        Comment findComment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("no such comment"));
        if (findComment.getAuthor().equals(findUser)) {
            return updateCommentBodyAndSave(updateRequest, findComment, commentRepository);
        }
        return updateFailAsUserNotFound(); // return null dto
    }

    private CommentDto.ResponseSave createCommentAndSave(CommentDto.RequestSave createRequest, Post findPost, User findUser) {
        String body = createRequest.getBody();
        Comment createComment = Comment.builder()
                .post(findPost)
                .user(findUser)
                .body(body)
                .build();

        Comment saveComment = commentRepository.save(createComment);
        return CommentDto.ResponseSave.builder()
                .commentId(saveComment.getId())
                .build();
    }
    private CommentDto.ResponseSave createChildCommentAndSave(CommentDto.RequestSave createRequest, Post findPost, User findUser, Comment findComment) {
        String body = createRequest.getBody();
        Comment createChildComment = Comment.builder()
                .post(findPost)
                .user(findUser)
                .body(body)
                .parentComment(findComment)
                .build();
        Comment saveComment = commentRepository.save(createChildComment);
        return CommentDto.ResponseSave.builder()
                .commentId(saveComment.getId())
                .build();
    }

    private CommentDto.ResponseSave updateCommentBodyAndSave(CommentDto.RequestSave updateRequest, Comment findComment, CommentRepository commentRepository) {
        String updateBody = updateRequest.getBody();
        findComment.updateBody(updateBody);

        Comment saveComment = commentRepository.save(findComment);
        return CommentDto.ResponseSave.builder()
                .commentId(saveComment.getId())
                .build();
    }

    private CommentDto.ResponseSave updateFailAsUserNotFound() {
        return CommentDto.ResponseSave.builder()
                .commentId(null)
                .build();

    }



}
