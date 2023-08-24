package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ImageAdapter;
import likelion.univ.domain.project.adapter.ProjectAdapter;
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

    private final ProjectAdapter projectAdapter;
    private final ImageAdapter imageAdapter;

//    @Transactional
//    public void addImage(Long id, ProjectRequestDto projectRequestDto) {
//        Project project = projectAdapter.findById(id).get();
//        if (projectRequestDto.getImages() != null) {
//            List<Image> images = new ArrayList<>();
//            for (ImageRequestDTO imageRequestDTO : projectRequestDto.getImages()) {
//                Image image = new Image(project, imageRequestDTO.getName(), imageRequestDTO.getSaved());
//                images.add(image);
//            }
//            imageAdapter.saveAll(images);
//        }
//    }

    @Transactional
    public void updateImage(Project project, List<Image> images) {
        imageAdapter.deleteByProject(project); //기존 사진 모두 삭제
        if(images != null) {
            imageAdapter.saveAll(images);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectAdapter.findById(id).get();
        imageAdapter.deleteByProject(project);
    }
}
