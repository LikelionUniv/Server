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

    @Column(length = 100)
    private String description;

    @Column(columnDefinition = "Text")
    private String content;

    private String productionUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="project_id", insertable = false, updatable = false)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="project_id", insertable = false, updatable = false)
    private List<Image> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="project_id", insertable = false, updatable = false)
    private List<ProjectTech> teches = new ArrayList<>();

    @Builder
    public Project(String thon, Output outPut, String serviceName, long ordinal, University univ, LocalDate startDate, LocalDate endDate, String description, String content, String productionUrl) {
        this.thon = thon;
        this.outPut = outPut;
        this.serviceName = serviceName;
        this.ordinal = ordinal;
        this.univ = univ;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.content = content;
        this.productionUrl = productionUrl;
    }

    public void updateUniv(University univ) {
        this.univ = univ;
    }

    public void update(Project updateProject) {
        this.thon = updateProject.getThon();
        this.outPut = updateProject.getOutPut();
        this.serviceName = updateProject.getServiceName();
        this.ordinal = updateProject.getOrdinal();
        this.univ = updateProject.getUniv();
        this.startDate = updateProject.getStartDate();
        this.endDate = updateProject.getEndDate();
        this.description = updateProject.getDescription();
        this.content = updateProject.getContent();
        this.productionUrl = updateProject.getProductionUrl();
    }
}