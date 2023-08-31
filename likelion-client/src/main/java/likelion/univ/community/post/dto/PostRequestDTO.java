package likelion.univ.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class PostRequestDTO {

    @Data
    @Builder
    @AllArgsConstructor
    public static class Save {

        private String title;

        private String body;
        private Long userId;

        private String thumbnail;

        private String mainCategory;

        private String subCategory;

    }

    @Data
    public static class Edit {
        private Long postId;
        private String title;

        private String body;

        private String thumbnail;
    }

    @Data
    public static class Delete {
        private Long postId;
    }



}
