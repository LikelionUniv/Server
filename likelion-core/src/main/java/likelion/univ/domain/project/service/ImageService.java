package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ImageService {

    private final ProjectAdaptor projectAdaptor;
    private final ImageAdaptor imageAdaptor;

    @Transactional
    public void addImage(List<Image> images) {
        if (images != null) {
            imageAdaptor.saveAll(images);
        }
    }

    @Transactional
    public void updateImage(Project project, List<Image> images) {
        imageAdaptor.deleteByProject(project); //기존 사진 모두 삭제
        if(images != null) {
            imageAdaptor.saveAll(images);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectAdaptor.findById(id);
        imageAdaptor.deleteByProject(project);
    }
}
