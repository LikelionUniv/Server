package likelion.univ.domain.community.comment.dto;

import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;


public class CommentServiceDto {
    @Data
    @Builder
    public static class CreateParentComment {
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
    public static class CUDResponse {
        private Long id;
    }

    @Data
    @Builder
    public static class ReadResponse {
        private Long id;
        private String username; // author.profile.name
        private Long parentId;
        private String body;
        private Boolean isDeleted;

        public static ReadResponse of(Comment comment) {
            return ReadResponse.builder()
                    .id(comment.getId())
                    .username(comment.getAuthor().getProfile().getName())
                    .parentId(comment.getParentComment().getId())
                    .body(comment.getBody())
                    .isDeleted(comment.getIsDeleted())
                    .build();
        }
    }



}
