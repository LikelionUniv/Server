package likelion.univ.domain.project.service;

import java.util.ArrayList;
import java.util.List;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.project.repository.ProjectTechRepository;
import likelion.univ.domain.project.repository.TechRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectTechService {

    private final TechRepository techRepository;
    private final ProjectRepository projectRepository;
    private final ProjectTechRepository projectTechRepository;

    @Transactional
    public void addProjectTech(Project project, List<String> techNames) {
        List<ProjectTech> projectTeches = new ArrayList<>();
        for (String techName : techNames) {
            Tech existingTech = techRepository.findByTechName(techName);
            Tech tech;
            if (existingTech != null) {
                tech = existingTech;
            } else {
                Tech newTech = Tech.builder().techName(techName).build();
                techRepository.save(newTech);
                tech = newTech;
            }
            ProjectTech projectTech = ProjectTech.builder()
                    .project(project)
                    .tech(tech)
                    .build();
            projectTeches.add(projectTech);
        }
        projectTechRepository.saveAll(projectTeches);
    }

    @Transactional
    public void updateProjectTech(Project project, List<String> teches) {
        projectTechRepository.deleteByProject(project);
        if (teches != null && !teches.isEmpty()) {
            List<ProjectTech> projectTeches = new ArrayList<>();

            for (String techName : teches) {
                Tech tech = techRepository.findByTechName(techName.toUpperCase());

                Tech persistedTech;
                if (tech == null) {
                    Tech newTech = Tech.builder().techName(techName.toUpperCase()).build();
                    techRepository.save(newTech);
                    persistedTech = newTech;
                } else {
                    persistedTech = tech;
                }

                ProjectTech projectTech = new ProjectTech(project, persistedTech);
                projectTeches.add(projectTech);
            }
            projectTechRepository.saveAll(projectTeches);
        }
    }

    @Transactional
    public void deleteProjectTech(Long id) {
        Project project = projectRepository.getById(id);
        projectTechRepository.deleteByProject(project);
    }
}
