package likelion.univ.domain.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import likelion.univ.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Example extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String columns;
    private String body;

    @Builder
    public Example(String column, String body) {
        this.columns = column;
        this.body = body;
    }

    public void updateColumn(String column) {
        this.columns = column;
    }

    //=======
//    @Builder
//    public Example(String body) {
//        this.body = body;
//    }
    public void editBody(String body) {
        this.body = body;
    }
}
