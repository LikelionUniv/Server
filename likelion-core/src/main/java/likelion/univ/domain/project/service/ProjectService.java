package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectAdaptor projectAdaptor;

    public Project createProject(Project project)
    {
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
