package likelion.univ.domain.entity;

import likelion.univ.domain.entity.common.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
}
