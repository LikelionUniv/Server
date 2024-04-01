package likelion.univ.domain.project.service;

import java.util.List;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectImageService {
    private final ProjectAdaptor projectAdaptor;
    private final ProjectImageAdaptor projectImageAdaptor;

    @Transactional
    public void addImage(List<ProjectImage> projectImages) {
        if (projectImages != null) {
            projectImageAdaptor.saveAll(projectImages);
        }
    }

    @Transactional
    public void updateImage(Project project, List<ProjectImage> projectImages) {
        projectImageAdaptor.deleteByProject(project); //기존 사진 모두 삭제
        if (projectImages != null) {
            projectImageAdaptor.saveAll(projectImages);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectAdaptor.findById(id);
        projectImageAdaptor.deleteByProject(project);
    }
}
