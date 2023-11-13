package likelion.univ.domain.commentlike.entity;

import likelion.univ.domain.like.commentlike.entity.CommentLike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommentLikeTest {

    @DisplayName("생성된 좋아요를 다시 누르면 좋아요를 취소한다.")
    @Test
    void switchLikeComment() {
        // given
        CommentLike commentLike = CommentLike.builder().build();
        Boolean beforeSwitch = commentLike.getIsCanceled();

        // when
        commentLike.switchLikeComment();

        // then
        assertThat(beforeSwitch).isFalse();
        assertThat(commentLike.getIsCanceled()).isTrue();
    }

    @DisplayName("취소한 좋아요를 다시 누르면 다시 좋아요를 할 수 있다.")
    @Test
    public void switchDislikeComment() throws Exception {
        // given
        CommentLike commentLike = CommentLike.builder().build();
        Boolean beforeSwitch = commentLike.getIsCanceled();

        // when
        commentLike.switchLikeComment();
        commentLike.switchLikeComment();

        // then
        assertThat(beforeSwitch).isFalse();
        assertThat(commentLike.getIsCanceled()).isFalse();
    }
}
