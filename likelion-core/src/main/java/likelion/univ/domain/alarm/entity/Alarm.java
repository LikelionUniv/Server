package likelion.univ.domain.alarm.entity;
import likelion.univ.common.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

import static likelion.univ.domain.alarm.entity.SendStatus.NOT_SENT;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ordinal;

    private String email;

    @Enumerated(EnumType.STRING)
    private SendStatus sendStatus;
}
