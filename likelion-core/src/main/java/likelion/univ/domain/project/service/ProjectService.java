package likelion.univ.domain.project.service;

import java.time.LocalDate;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.CreateProjectBadRequestException;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        if (startDate.isEqual(endDate) || startDate.isAfter(endDate)) {
            throw new CreateProjectBadRequestException();
        }
        try {
            Project savedProject = projectRepository.save(project);
            return savedProject;
        } catch (Exception e) {
            throw new CreateProjectBadRequestException();
        }
    }

    public void updateProject(Long id, Project updateProject) {
        Project project = projectRepository.getById(id);
        project.update(updateProject);
        projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.getById(id);
        projectRepository.delete(project);
    }
}
