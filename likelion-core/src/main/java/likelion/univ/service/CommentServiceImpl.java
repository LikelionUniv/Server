package likelion.univ.service;

import likelion.univ.domain.dto.CommentDto;
import likelion.univ.domain.dto.common.CommonResponseDto;
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
    public CommonResponseDto<Object> createParentComment(CommentDto.CreateParent request) {
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
    public CommonResponseDto<Object> createChildComment(CommentDto.CreateChild request) {
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
    public CommonResponseDto<Object> updateCommentBody(Long commentId, CommentDto.UpdateComment request) {
        String body;
        User findUser;
        Comment findComment;
        try {
            body = request.getBody();
            findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
            findComment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("no such comment"));
            if (isCommentAuthor(findUser, findComment)) {
                return saveComment(findComment.updateBody(body));
            }
        } catch (NoSuchElementException e) {
            return responseException();
        }
        return updateFail();
    }

    @Override
    public CommonResponseDto<Object> deleteComment(Long commentId, CommentDto.DeleteComment request) {
        User findUser;
        Comment findComment;
        try {
            findUser = userRepository.findById(request.getUserId()).orElseThrow(() -> new NoSuchElementException("no such user"));
            findComment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("no such comment"));
            if (isCommentAuthor(findUser, findComment)) {
                return saveComment(findComment.delete()); // soft delete
            }
        } catch (NoSuchElementException e) {
            return responseException();
        }
        return deleteFail();
    }

    /* ------------------------ 내부메서드 ------------------------ */
    private static CommonResponseDto<Object> responseException() {
        return CommonResponseDto.builder()
                .message("잘못된 요청입니다.")
                .build();
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

    private CommonResponseDto<Object> saveComment(Comment comment) {
        Comment saveComment = commentRepository.save(comment);
        return CommonResponseDto.builder()
                .data(saveComment.getId())
                .message("성공적으로 저장되었습니다.")
                .build();
    }


    private CommonResponseDto<Object> updateFail() {
        return CommonResponseDto.builder()
                .message("본인이 작성한 댓글만 수정할 수 있습니다.")
                .build();
    }
    private static CommonResponseDto<Object> deleteFail() {
        return CommonResponseDto.builder()
                .message("본인이 작성한 댓글만 삭제할 수 있습니다.")
                .build();
    }

}

