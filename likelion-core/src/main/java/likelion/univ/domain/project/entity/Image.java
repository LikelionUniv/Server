package likelion.univ.domain.project.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    //원본 파일명
    private String name;

    // 등록 파일명
    private String saved;

    @Builder
    public Image(Project project, String name, String saved) {
        this.project = project;
        this.name = name;
        this.saved = saved;
    }
}