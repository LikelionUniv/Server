package likelion.univ.domain.comment.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.like.commentlike.entity.CommentLike;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "comment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();


    @OneToMany(mappedBy = "parentComment",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Builder
    private Comment(Post post, User author, String body) {
        this.parentComment = null;
        this.post = post;
        this.author = author;
        this.body = body;
        this.isDeleted = false;
    }

    public Long updateBody(String body) {
        this.body = body;
        return this.id;
    }
    public Long softDelete() {
        this.isDeleted = true;
        return this.id;
    }

    /* 연관관계 편의 메서드 */
    public void setParent(Comment parent) {
        this.parentComment = parent;
        parent.getChildComments().add(this);
    }
}
