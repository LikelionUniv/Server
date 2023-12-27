package likelion.univ.domain.like.postlike.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;

    @Builder
    private PostLike(Long id, User user, Post post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }
}
