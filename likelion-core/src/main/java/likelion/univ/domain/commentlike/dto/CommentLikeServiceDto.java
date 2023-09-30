package likelion.univ.domain.commentlike.dto;

import likelion.univ.domain.commentlike.entity.CommentLike;
import lombok.Builder;
import lombok.Data;

public class CommentLikeServiceDto {
    @Data
    @Builder
    public static class createLikeCommentRequest {
        private Long userId;
        private Long commentId;
    }

    @Data
    @Builder
    public static class switchLikeCommentRequest {
        private Long likeCommentId;
        private Long userId;
    }


    @Data
    @Builder
    public static class CommandResponse {
        private Long id;

        public static CommandResponse of(CommentLike commentLike) {
            return CommandResponse.builder()
                    .id(commentLike.getId())
                    .build();
        }
    }
}
