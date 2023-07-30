package likelion.univ.domain.dto;

import lombok.Builder;
import lombok.Data;

public class CommentDto {
    @Data
    @Builder
    public static class CreateParent {
        private Long postId;
        private Long userId;
        private String body;
    }

    @Data
    @Builder
    public static class CreateChild {
        private Long postId;
        private Long userId;
        private Long parentId;
        private String body;
    }

    @Data
    @Builder
    public static class UpdateComment {
        private Long userId;
        private String body;
    }

    @Data
    @Builder
    public static class DeleteComment {
        private Long userId;
    }
}
