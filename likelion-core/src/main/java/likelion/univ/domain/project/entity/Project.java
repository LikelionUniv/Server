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
    private String service;

    private int year;

    private String univ; // University DB와 연결?

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(nullable = false)
    private String member;

    private String tech;

    @Column(columnDefinition = "Text")
    private String description;

    @Column(columnDefinition = "Text")
    private String content;

    private String link;

    @Builder
    public Project(String thon, Output outPut, String service, int year, String univ, LocalDate startDate, LocalDate endDate, String member, String tech, String description, String content, String link) {
        this.thon = thon;
        this.outPut = outPut;
        this.service = service;
        this.year = year;
        this.univ = univ;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.tech = tech;
        this.description = description;
        this.content = content;
        this.link = link;
    }

    public void update(ProjectSimpleDto projectSimpleDto) {
        this.thon = projectSimpleDto.getThon();
        this.outPut = projectSimpleDto.getOutPut();
        this.service = projectSimpleDto.getService();
        this.year = projectSimpleDto.getYear();
        this.univ = projectSimpleDto.getUniv();
        this.startDate = projectSimpleDto.getStartDate();
        this.endDate = projectSimpleDto.getEndDate();
        this.member = projectSimpleDto.getMember();
        this.tech = projectSimpleDto.getTech();
        this.description = projectSimpleDto.getDescription();
        this.content = projectSimpleDto.getContent();
        this.link = projectSimpleDto.getLink();
    }
}
