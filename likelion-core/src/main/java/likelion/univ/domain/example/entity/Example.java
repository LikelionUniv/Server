package likelion.univ.domain.example.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "example")
public class Example extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "example_id")
    private Long id;

    @Column(nullable = false)
    private String columns;
    @Builder
    public Example(String column) {
        this.columns = column;
    }
    public void updateColumn(String column){
        this.columns = column;
    }
}
