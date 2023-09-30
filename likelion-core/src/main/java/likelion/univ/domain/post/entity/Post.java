package likelion.univ.domain.post.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.post.dto.PostUpdateServiceDto;
import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import likelion.univ.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Post extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(length = 500)
    private String title;

    @Column(length = 50000)
    private String body;

    private String thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_category")
    private MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_category")
    private SubCategory subCategory;


    public void edit(PostUpdateServiceDto request){
        if(request.getTitle() != null)
            this.title = request.getTitle();
        if(request.getBody() != null)
            this.body = request.getBody();
        if(request.getThumbnail() == null)
            this.thumbnail = null ;
        else if (!request.getThumbnail().equals(this.getThumbnail()))
            this.thumbnail = request.getThumbnail();
    }

}
