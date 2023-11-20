package likelion.univ.domain.like.postlike.entity;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD:likelion-core/src/main/java/likelion/univ/domain/like/postlike/entity/PostLike.java
    @Column(name = "like_post_id")
=======
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484:likelion-core/src/main/java/likelion/univ/domain/post/entity/LikePost.java
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;

}
