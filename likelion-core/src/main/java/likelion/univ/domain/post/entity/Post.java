package likelion.univ.domain.post.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.dto.request.PostUpdateServiceDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(length = 100)
    private String title;

    @Column(columnDefinition="TEXT")
    private String body;

    private String thumbnail;

    @OneToMany(mappedBy = "post",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id", insertable = false, updatable = false)
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;


    public void edit(PostUpdateServiceDto request) {
        if (request.getTitle() != null)
            this.title = request.getTitle();
        if (request.getBody() != null)
            this.body = request.getBody();
        if (request.getThumbnail() == null)
            this.thumbnail = null;
        else if (!request.getThumbnail().equals(this.getThumbnail()))
            this.thumbnail = request.getThumbnail();
    }

}
