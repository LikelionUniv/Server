package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ProjectImageAdaptor {

    private final ProjectImageRepository projectImageRepository;

    public List<ProjectImage> findByProject(Project project) {
        return projectImageRepository.findByProject(project);
    }

    public void saveAll(List<ProjectImage> projectImages) {
        projectImageRepository.saveAll(projectImages);
    }

    public void deleteByProject(Project project) {
        projectImageRepository.deleteByProject(project);
    }

    public void save(ProjectImage projectImage){
        projectImageRepository.save(projectImage);
    }
}
