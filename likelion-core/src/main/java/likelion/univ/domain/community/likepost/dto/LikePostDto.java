package likelion.univ.domain.community.likepost.dto;

import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

public class LikePostDto {

    @Data
    @Builder
    @AllArgsConstructor
    public static class CreateRequest {

        private User author;

        private Post post;


    }

    @Data
    @Builder
    public static class ResponseDTO {


        private Long id;


    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class DeleteRequest {
        private User author;

        private Post post;
    }

}
