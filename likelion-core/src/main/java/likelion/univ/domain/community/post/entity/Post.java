package likelion.univ.domain.community.post.entity;

import likelion.univ.domain.common.BaseTimeEntity;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.domain.community.post.entity.enums.MainCategory;
import likelion.univ.domain.community.post.entity.enums.SubCategory;
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


//    public void update(PostServiceDTO.Update updateRequest){
//        if(updateRequest.getTitle() != null)
//            this.title = updateRequest.getTitle();
//        if(updateRequest.getBody() != null)
//            this.body = updateRequest.getBody();
//        if(updateRequest.getThumbnail() == null)
//            this.thumbnail = null ;
//        else if (!updateRequest.getThumbnail().equals(this.getThumbnail()))
//            this.thumbnail = updateRequest.getThumbnail();
//    }

}
