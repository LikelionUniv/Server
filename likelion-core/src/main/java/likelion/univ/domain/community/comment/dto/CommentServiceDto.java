package likelion.univ.domain.community.comment.dto;

import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;


public class CommentServiceDto {
    @Data
    @Builder
    public static class CreateChildComment {
        private String body;
        private Post post;
        private User user;
    }

    @Data
    @Builder
    public static class CreateChildComment {
        private String body;
        private Post post;
        private User user;
        private Comment parent;
    }

    @Data
    @Builder
    public static class EditComment {
        private Long id;
        private String body;
    }

    @Data
    @Builder
    public static class DeleteComment {
        private Long id;
    }

    @Data
    @Builder
    public static class Response {
        private Long id;
    }

}
