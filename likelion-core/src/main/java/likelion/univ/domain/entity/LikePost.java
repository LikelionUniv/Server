package likelion.univ.domain.entity;


import likelion.univ.domain.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class LikePost {

    @Id
    @GeneratedValue
    @Column(name = "like_post_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;



    @Column(name = "post_title")
    private String postTitle;




}
