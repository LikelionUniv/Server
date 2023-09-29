package likelion.univ.comment.dto;

import lombok.Builder;
import lombok.Data;

public class CommentRequestDto {
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
    public static class EditComment {
        private Long userId;
        private String body;
    }

    @Data
    @Builder
    public static class DeleteComment {
        private Long userId;
    }
}
