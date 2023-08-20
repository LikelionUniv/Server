package likelion.univ.domain.project.entity;

import likelion.univ.domain.project.dto.ProjectSimpleDto;
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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private long id;

    // private String thumbnail;

    private String thon;

    @Enumerated(EnumType.STRING)
    private Output outPut;

    @Column(nullable = false)
    private String serviceName; //서비스명

    private int ordinal; //기수

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

    @Column(columnDefinition = "Text")
    private String description;

    @Column(columnDefinition = "Text")
    private String content;

    private String projectUrl;

    @Builder
    public Project(String thon, Output outPut, String serviceName, int ordinal, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String projectUrl) {
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

    public void update(ProjectSimpleDto projectSimpleDto) {
        this.thon = projectSimpleDto.getThon();
        this.outPut = projectSimpleDto.getOutPut();
        this.serviceName = projectSimpleDto.getServiceName();
        this.ordinal = projectSimpleDto.getOrdinal();
        this.univ = projectSimpleDto.getUniv();
        this.startDate = projectSimpleDto.getStartDate();
        this.endDate = projectSimpleDto.getEndDate();
        this.tech = projectSimpleDto.getTech();
        this.description = projectSimpleDto.getDescription();
        this.content = projectSimpleDto.getContent();
        this.projectUrl = projectSimpleDto.getProjectUrl();
    }
}
