package likelion.univ.domain.community.likepost.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.community.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class LikePost extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "like_post_id")
    private Long likeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id"  )
    private Post post;



    @Column(name = "post_title")
    private String postTitle;




}
