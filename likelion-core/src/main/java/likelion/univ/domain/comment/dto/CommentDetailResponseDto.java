package likelion.univ.domain.comment.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDetailResponseDto(
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
    public static CommentDetailResponseDto of(ParentCommentDetailResponseDto parent, List<ChildCommentDetailResponseDto> childs) {
        List<ChildCommentDetailResponseDto> children = childs.stream().filter(i -> i.parentId().equals(parent.commentId())).toList();
        return CommentDetailResponseDto.builder()
                .commentId(parent.commentId())
                .userId(parent.userId())
                .userName(parent.userName())
                .userProfileImageUrl(parent.userProfileImageUrl())
                .isAuthorComment(parent.isAuthorComment())
                .likeCount(parent.likeCount())
                .body(parent.body())
                .isDeleted(parent.isDeleted())
                .childComments(children)
                .createdDate(parent.createdDate())
                .build();
    }

    @Builder
    public CommentDetailResponseDto(Long commentId, Long userId, String userName, String userProfileImageUrl, Boolean isAuthorComment, Integer likeCount, String body, Boolean isDeleted, List<ChildCommentDetailResponseDto> childComments, LocalDateTime createdDate) {
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
}
