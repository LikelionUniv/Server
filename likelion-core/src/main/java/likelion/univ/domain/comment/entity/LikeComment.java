package likelion.univ.domain.comment.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeComment extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Boolean isCanceled;

    @Builder
    public LikeComment(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
        this.isCanceled = false;
    }

    public LikeComment switchLikeComment() {
        this.isCanceled = !this.getIsCanceled();
        return this;
    }
}
