package likelion.univ.domain.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.entity.enums.MainCategory;
import likelion.univ.domain.entity.enums.SubCategory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

}
