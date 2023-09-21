package likelion.univ.domain.project.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private long id;

    private String thon;

    @Enumerated(EnumType.STRING)
    private Output outPut;

    @Column(nullable = false)
    private String serviceName; //서비스명

    private long ordinal; //기수

    private String univ; // University DB와 연결?

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

//    projectMember 엔티티에서 구현
//    @Column(nullable = false)
//    private String member;

    private String tech;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "Text")
    private String content;

    private String projectUrl;

    @Builder
    public Project(String thon, Output outPut, String serviceName, long ordinal, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String projectUrl) {
        this.thon = thon;
        this.outPut = outPut;
        this.serviceName = serviceName;
        this.ordinal = ordinal;
        this.univ = univ;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tech = tech;
        this.description = description;
        this.content = content;
        this.projectUrl = projectUrl;
    }

    public void update(String thon, Output output, String serviceName, long ordinal, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String projectUrl) {
        this.thon = thon;
        this.outPut = output;
        this.serviceName = serviceName;
        this.ordinal = ordinal;
        this.univ = univ;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tech = tech;
        this.description = description;
        this.content = content;
        this.projectUrl = projectUrl;
    }
}
