package likelion.univ.domain.postlike.entity;

import likelion.univ.domain.postlike.dto.PostLikeCreateServiceDto;
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
    @Column(name = "like_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;

}
