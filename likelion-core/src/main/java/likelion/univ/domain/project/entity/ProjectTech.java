package likelion.univ.domain.project.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectTech {
    @Id
    @GeneratedValue
    @Column(name = "project_tech_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private String tech;

    @Builder
    public ProjectTech(Project project,String tech) {
        this.project = project;
        this.tech = tech;
    }
}
