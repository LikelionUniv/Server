package likelion.univ.domain.community.comment.service;

import likelion.univ.domain.community.comment.dto.CommentRequestDto;
import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.community.comment.repository.CommentRepository;
import likelion.univ.domain.community.post.repository.PostRepository;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.response.SuccessResponse;
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
    public SuccessResponse<Object> createParentComment(CommentRequestDto.CreateParent request) {
        String body;
        Post findPost;
        User findUser;
        try {
            body = request.getBody();
            findPost = postRepository.findById(request.getPostId()).orElseThrow(() -> new NoSuchElementException("no such post"));
            findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
        } catch (NoSuchElementException e) {
            return responseException();
        }
        return saveComment(createParentComment(body, findPost, findUser));
    }

    @Override
    public SuccessResponse<Object> createChildComment(CommentRequestDto.CreateChild request) {
        String body;
        Post findPost;
        User findUser;
        Comment findComment;
        try {
            body = request.getBody();
            findPost = postRepository.findById(request.getPostId()).orElseThrow(() -> new NoSuchElementException("no such post"));
            findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
            findComment = commentRepository.findById(request.getParentId()).orElseThrow(() -> new NoSuchElementException("no such comment"));
        } catch (NoSuchElementException e) {
            return responseException();
        }
        return saveComment(createChildComment(body, findPost, findUser, findComment));
    }


    @Override
    public SuccessResponse<Object> editCommentBody(Long commentId, CommentRequestDto.EditComment request) {
        String body;
        User findUser;
        Comment findComment;
        body = request.getBody();
        findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
        findComment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("no such comment"));
        if (isCommentAuthor(findUser, findComment)) {
            return saveComment(findComment.editBody(body));
        } else {
            throw new NoSuchElementException("error");
        }
    }

    @Override
    public SuccessResponse<Object> deleteComment(Long commentId, CommentRequestDto.DeleteComment request) {
        User findUser;
        Comment findComment;
        findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
        findComment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("no such comment"));
        if (isCommentAuthor(findUser, findComment)) {
            return saveComment(findComment.delete()); // soft delete
        } else {
            throw new NoSuchElementException("error");
        }
    }

    /* ------------------------ 내부메서드 ------------------------ */
    private static SuccessResponse<Object> responseException() {
        return SuccessResponse.of("잘못된 요청입니다.");
    }

    private static boolean isCommentAuthor(User findUser, Comment findComment) {
        return findComment.getAuthor().equals(findUser);
    }

    private Comment createParentComment(String body, Post findPost, User findUser) {
        return Comment.builder()
                .post(findPost)
                .user(findUser)
                .body(body)
                .build();
    }

    private Comment createChildComment(String body, Post findPost, User findUser, Comment findComment) {
        return Comment.builder()
                .post(findPost)
                .user(findUser)
                .body(body)
                .parentComment(findComment)
                .build();
    }

    private SuccessResponse<Object> saveComment(Comment comment) {
        Comment saveComment = commentRepository.save(comment);
        return SuccessResponse.of(saveComment);
    }

}

