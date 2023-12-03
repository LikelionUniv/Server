package likelion.univ.domain.comment.dto;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record ParentCommentDetailResponseDto(
        Long commentId,
        Long userId,
        String userName,
        String userProfileImageUrl,
        Boolean isAuthorComment,
        Integer likeCount,
        String body,
        Boolean isDeleted,
        List<ChildCommentDetailResponseDto> childComments,
        LocalDateTime createdDate
) {
    public ParentCommentDetailResponseDto(Long commentId, Long userId, String userName, String userProfileImageUrl, Boolean isAuthorComment, Integer likeCount, String body, Boolean isDeleted, List<ChildCommentDetailResponseDto> childComments, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.userProfileImageUrl = userProfileImageUrl;
        this.isAuthorComment = isAuthorComment;
        this.likeCount = likeCount;
        this.body = body;
        this.isDeleted = isDeleted;
        this.childComments = childComments;
        this.createdDate = createdDate;
    }

    public static ParentCommentDetailResponseDto of(Comment parentComment, User loginUser) {
        return ParentCommentDetailResponseDto.builder()
                .commentId(parentComment.getId())
                .userId(parentComment.getAuthor().getId())
                .userName(parentComment.getAuthor().getProfile().getName())
                .userProfileImageUrl(parentComment.getAuthor().getProfile().getProfileImage())
                .isAuthorComment(parentComment.getAuthor().equals(loginUser))
                .likeCount(getLikeCount(parentComment))
                .body(parentComment.getBody())
                .isDeleted(parentComment.getIsDeleted())
                .childComments(getChildCommentsDto(parentComment.getChildComments(), loginUser))
                .createdDate(parentComment.getCreatedDate())
                .build();
    }
    private static Integer getLikeCount(Comment comment) {
        return Math.toIntExact(comment.getCommentLikes().stream()
                .filter(l -> l.getIsCanceled().equals(false))
                .count());
    }
    private static List<ChildCommentDetailResponseDto> getChildCommentsDto(List<Comment> childComments, User loginUser) {
        return childComments.stream().map(c -> ChildCommentDetailResponseDto.of(c, loginUser)).toList();
    }
}
