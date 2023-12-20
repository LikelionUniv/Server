package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ProjectImageAdaptor {

    private final ProjectImageRepository projectImageRepository;

    public List<Image> findByProject(Project project) {
        return projectImageRepository.findByProject(project);
    }

    public void saveAll(List<Image> images) {
        projectImageRepository.saveAll(images);
    }

    public void deleteByProject(Project project) {
        projectImageRepository.deleteByProject(project);
    }

    public void save(Image image){
        projectImageRepository.save(image);
    }
}
