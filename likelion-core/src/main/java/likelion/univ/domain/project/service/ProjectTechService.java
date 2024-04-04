package likelion.univ.domain.project.service;

import java.util.ArrayList;
import java.util.List;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectTechService {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectTechAdaptor projectTechAdaptor;

    @Transactional
    public void addProjectTech(Project project, List<String> techNames) {
        List<ProjectTech> projectTeches = new ArrayList<>();
        for (String techName : techNames) {
            Tech existingTech = projectTechAdaptor.findByName(techName);
            Tech tech;
            if (existingTech != null) {
                tech = existingTech;
            } else {
                Tech newTech = Tech.builder().techName(techName).build();
                projectTechAdaptor.saveTech(newTech);
                tech = newTech;
            }
            ProjectTech projectTech = ProjectTech.builder()
                    .project(project)
                    .tech(tech)
                    .build();
            projectTeches.add(projectTech);
        }
        projectTechAdaptor.saveAll(projectTeches);
    }

    @Transactional
    public void updateProjectTech(Project project, List<String> teches) {
        projectTechAdaptor.deleteByProject(project);
        if (teches != null && !teches.isEmpty()) {
            List<ProjectTech> projectTeches = new ArrayList<>();

            for (String techName : teches) {
                Tech tech = projectTechAdaptor.findByName(techName.toUpperCase());

                Tech persistedTech;
                if (tech == null) {
                    Tech newTech = Tech.builder().techName(techName.toUpperCase()).build();
                    projectTechAdaptor.saveTech(newTech);
                    persistedTech = newTech;
                } else {
                    persistedTech = tech;
                }

                ProjectTech projectTech = new ProjectTech(project, persistedTech);
                projectTeches.add(projectTech);
            }
            projectTechAdaptor.saveAll(projectTeches);
        }
    }

    @Transactional
    public void deleteProjectTech(Long id) {
        Project project = projectAdaptor.findById(id);
        projectTechAdaptor.deleteByProject(project);
    }
}
