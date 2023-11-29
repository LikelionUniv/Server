package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            List<Tech> existingTech = projectTechAdaptor.findByName(techName);
            Tech tech;
            if (!existingTech.isEmpty()) {
                tech = existingTech.get(0);
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
    public void updateProjectTech(Project project, List<Tech> teches) {
        projectTechAdaptor.deleteByProject(project);
        if (teches != null && !teches.isEmpty()) {
            List<ProjectTech> projectTeches = new ArrayList<>();

            for (Tech tech : teches) {
                List<Tech> techList = projectTechAdaptor.findByName(tech.getTechName());
                Tech persistedTech = techList.isEmpty() ? tech : techList.get(0);

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
