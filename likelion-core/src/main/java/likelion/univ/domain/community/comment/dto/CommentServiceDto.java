package likelion.univ.domain.community.comment.dto;

import likelion.univ.domain.community.comment.entity.Comment;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;


public class CommentServiceDto {
    @Data
    @Builder
    public static class CreateParentCommentRequest {
        private String body;
        private Post post;
        private User user;
    }

    @Data
    @Builder
    public static class CreateChildCommentRequest {
        private String body;
        private Post post;
        private User user;
        private Comment parent;
    }

    @Data
    @Builder
    public static class EditCommentRequest {
        private Long id;
        private String body;
    }

    @Data
    @Builder
    public static class DeleteCommentRequest {
        private Long id;
    }

    @Data
    @Builder
    public static class CommandResponse {
        private Long id;

        public static CommandResponse of(Comment comment) {
            return CommandResponse.builder()
                    .id(comment.getId())
                    .build();
        }
    }

    @Data
    @Builder
    public static class ReadResponse {
        private Long id;
        private String username; // author.profile.name
        private Long parentId;
        private String body;
        private Long likeCount;
        private Boolean isDeleted;

        public static ReadResponse of(Comment comment) {
            return ReadResponse.builder()
                    .id(comment.getId())
                    .username(comment.getAuthor().getProfile().getName())
                    .parentId(comment.getParentComment().getId())
                    .body(comment.getBody())
                    .likeCount(getLikeCount(comment))
                    .isDeleted(comment.getIsDeleted())
                    .build();
        }

        private static long getLikeCount(Comment comment) {
            return comment.getLikeComments().stream()
                    .filter(l -> l.getIsCanceled().equals(false))
                    .count();
        }
    }



}
