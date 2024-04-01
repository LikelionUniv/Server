package likelion.univ.domain.post.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.like.postlike.entity.PostLike;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.dto.request.UpdatePostCommand;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(columnDefinition = "TEXT")
    private String body;

    private String thumbnail;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private List<PostLike> postLikes = new ArrayList();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    private SubCategory subCategory;

    public void edit(UpdatePostCommand request) {
        if (request.title() != null) {
            this.title = request.title();
        }
        if (request.body() != null) {
            this.body = request.body();
        }
        if (request.thumbnail() == null) {
            this.thumbnail = null;
        } else if (!request.thumbnail().equals(this.getThumbnail())) {
            this.thumbnail = request.thumbnail();
        }
        if (!request.mainCategory().isBlank()) {
            this.mainCategory = MainCategory.findByTitle(request.mainCategory());
        }
        if (!request.subCategory().isBlank()) {
            this.subCategory = SubCategory.findByTitle(request.subCategory());
        }
    }

}
