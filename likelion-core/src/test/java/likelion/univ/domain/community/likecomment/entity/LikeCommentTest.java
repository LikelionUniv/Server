package likelion.univ.domain.community.likecomment.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LikeCommentTest {

    @DisplayName("생성된 좋아요를 다시 누르면 좋아요를 취소한다.")
    @Test
    void switchLikeComment() {
        // given
        LikeComment likeComment = LikeComment.builder().build();
        Boolean beforeSwitch = likeComment.getIsCanceled();

        // when
        likeComment.switchLikeComment();

        // then
        assertThat(beforeSwitch).isFalse();
        assertThat(likeComment.getIsCanceled()).isTrue();
    }

    @DisplayName("취소한 좋아요를 다시 누르면 다시 좋아요를 할 수 있다.")
    @Test
    public void switchDislikeComment() throws Exception {
        // given
        LikeComment likeComment = LikeComment.builder().build();
        Boolean beforeSwitch = likeComment.getIsCanceled();

        // when
        likeComment.switchLikeComment();
        likeComment.switchLikeComment();

        // then
        assertThat(beforeSwitch).isFalse();
        assertThat(likeComment.getIsCanceled()).isFalse();
    }
}
