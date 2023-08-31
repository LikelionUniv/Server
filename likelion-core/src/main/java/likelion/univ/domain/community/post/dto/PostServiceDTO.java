package likelion.univ.domain.community.post.dto;

import likelion.univ.domain.community.post.entity.enums.MainCategory;
import likelion.univ.domain.community.post.entity.enums.SubCategory;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class PostServiceDTO {

    @Data
    @Builder
    public static class Retrieve {


        private Long id;

        private Long authorId;
        private String author;

        private String title;

        private String body;

        private String thumbnail;

        private MainCategory mainCategory;

        private SubCategory subCategory;

    }

    @Data
    @Builder
    public static class CreateResponse {


        private Long id;


    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class CreateRequest {

        private String title;

        private String body;
        private User user;

        private String thumbnail;

        private String mainCategory;

        private String subCategory;

    }
}
