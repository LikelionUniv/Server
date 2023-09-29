package likelion.univ.likepost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class LikePostRequestDto {

    @Data
    @Builder
    @AllArgsConstructor
    public static class Save {


        private Long userId;

        private Long postId;

    }

    @Data
    public static class Delete {

        private Long userId;
        private Long postId;
    }
}
