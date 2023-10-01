package likelion.univ.domain.like.commentlike.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean isCanceled;

    @Builder
    public CommentLike(User user, Comment comment, Boolean isCanceled) {
        this.user = user;
        this.comment = comment;
        this.isCanceled = isCanceled;
    }

    public CommentLike switchLikeComment() {
        this.isCanceled = !this.getIsCanceled();
        return this;
    }
}
