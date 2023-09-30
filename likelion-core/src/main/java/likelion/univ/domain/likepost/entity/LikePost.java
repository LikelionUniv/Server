package likelion.univ.domain.likepost.entity;

import likelion.univ.domain.likepost.dto.LikePostCreateRequestDto;
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
public class LikePost   {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;

    public static LikePost of(LikePostCreateRequestDto request) {
        return LikePost.builder()
                .post(request.getPost())
                .author(request.getAuthor())
                .build();
    }
}
