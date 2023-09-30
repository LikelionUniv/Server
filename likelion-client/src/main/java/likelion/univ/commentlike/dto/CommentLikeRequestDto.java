package likelion.univ.commentlike.dto;

import lombok.Builder;
import lombok.Data;

public class CommentLikeRequestDto {
    @Data
    @Builder
    public static class Create {
        private Long userId;
        private Long commentId;
    }

    @Data
    @Builder
    public static class Switch {
        private Long userId;
    }

}
