package likelion.univ.domain.project.service;

import java.util.List;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectImageService {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;

    @Transactional
    public void addImage(List<ProjectImage> projectImages) {
        if (projectImages != null) {
            projectImageRepository.saveAll(projectImages);
        }
    }

    @Transactional
    public void updateImage(Project project, List<ProjectImage> projectImages) {
        projectImageRepository.deleteByProject(project); //기존 사진 모두 삭제
        if (projectImages != null) {
            projectImageRepository.saveAll(projectImages);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectRepository.getById(id);
        projectImageRepository.deleteByProject(project);
    }
}
