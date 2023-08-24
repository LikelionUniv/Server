package likelion.univ.community.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class PostDto {

    @Data
    @Builder
    @AllArgsConstructor
    public static class Save {

        private String title;

        private String body;

        private String thumbnail;

        private String mainCategory;

        private String subCategory;

    }

    @Data
    public static class Update {//카테고리는 변경할 수 없는지?
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
