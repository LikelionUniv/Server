package likelion.univ.domain.dto;

import lombok.Builder;
import lombok.Data;
public class CommentDto {
    @Data
    @Builder
    public static class ResponseSave {
        private Long commentId;
    }

    @Data
    @Builder
    public static class RequestSave {
        private String body;
    }

    public static class RequestDelete {
        private Long commentId;
    }
}
