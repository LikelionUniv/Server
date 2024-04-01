package likelion.univ.domain.project.service;

import java.time.LocalDate;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.CreateProjectBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectAdaptor projectAdaptor;

    public Project createProject(Project project) {
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        if (startDate.isEqual(endDate) || startDate.isAfter(endDate)) {
            throw new CreateProjectBadRequestException();
        }
        Project savedProject = projectAdaptor.save(project);
        return savedProject;
    }

    public void updateProject(Long id, Project updateProject) {
        Project project = projectAdaptor.findById(id);
        project.update(updateProject);
        projectAdaptor.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectAdaptor.findById(id);
        projectAdaptor.delete(project);
    }
}
