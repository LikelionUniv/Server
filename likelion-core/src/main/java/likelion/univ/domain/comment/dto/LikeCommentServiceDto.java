package likelion.univ.domain.comment.dto;

import likelion.univ.domain.comment.entity.LikeComment;
import lombok.Builder;
import lombok.Data;

public class LikeCommentServiceDto {
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

        public static CommandResponse of(LikeComment likeComment) {
            return CommandResponse.builder()
                    .id(likeComment.getId())
                    .build();
        }
    }
}
