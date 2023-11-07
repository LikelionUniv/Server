package likelion.univ.domain.project.entity;

import likelion.univ.common.entity.BaseTimeEntity;
import likelion.univ.domain.project.entity.enums.Output;
import likelion.univ.domain.university.entity.University;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University univ;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String tech;

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "Text")
    private String content;

    private String productionUrl;

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private List<ProjectTech> techList = new ArrayList<>();

    @Builder
    public Project(String thon, Output outPut, String serviceName, long ordinal, University univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String productionUrl) {
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
        this.productionUrl = productionUrl;
    }
}
