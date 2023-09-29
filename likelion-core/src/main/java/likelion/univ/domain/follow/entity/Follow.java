package likelion.univ.domain.follow.entity;

<<<<<<< HEAD:likelion-core/src/main/java/likelion/univ/domain/follow/entity/Follow.java
import likelion.univ.common.entity.BaseTimeEntity;
=======
>>>>>>> 4b4ff2799b67a4ed393a628bae18a933d8e924b8:likelion-core/src/main/java/likelion/univ/domain/follow/entity/follow.java
import likelion.univ.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Follow extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_id")
    private User followee;


}
