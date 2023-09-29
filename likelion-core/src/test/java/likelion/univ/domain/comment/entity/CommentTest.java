package likelion.univ.domain.comment.entity;

import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CommentTest {

    @DisplayName("댓글의 내용을 수정한다.")
    @Test
    void editBody() {
        // given
        String prev = "이전 내용";
        String next = "변경 내용";
        Comment comment = Comment.builder()
                .body(prev)
                .build();
        String getPrev = comment.getBody();

        // when
        comment.editBody(next);

        // then
        assertThat(getPrev).isEqualTo(prev);
        assertThat(comment.getBody()).isEqualTo(next);
    }

    @DisplayName("댓글 삭제를 누르면 soft delete된다.")
    @Test
    void deleteByAuthor() {
        // given
        Long id = 1L;
        User author = User.builder().id(1L).build();
        Comment comment = Comment.builder()
                .author(author)
                .build();
        Boolean prevIsDeleted = comment.getIsDeleted();

        // when
        comment.delete();

        // then
        assertThat(prevIsDeleted).isEqualTo(false);
        assertThat(comment.getIsDeleted()).isEqualTo(true);

    }


    @DisplayName("대댓글 생성시 대댓글에 부모 댓글을 설정한다.")
    @Test
    void setParent() {
        // given
        Comment parentComment = Comment.builder().build();
        Comment childComment1 = Comment.builder().build();
        Comment childComment2 = Comment.builder().build();

        Comment prevParentValue = childComment1.getParentComment();

        // when
        childComment1.setParent(parentComment);
        childComment2.setParent(parentComment);

        // then
        assertThat(prevParentValue).isNull();
        assertThat(childComment1.getParentComment()).isEqualTo(parentComment);
        assertThat(parentComment.getChildComments().get(0)).isEqualTo(childComment1);
        assertThat(parentComment.getChildComments().get(1)).isEqualTo(childComment2);
    }
}
