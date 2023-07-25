package likelion.univ.domain.dto;

public class CommentDto {
    public static class Save {
        private String body;
    }

    public static class Delete {
        private Long commentId;
    }
}
