package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ImageAdapter;
import likelion.univ.domain.project.adapter.ProjectAdapter;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.dto.ProjectSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ImageService {

    private final ProjectAdapter projectAdapter;
    private final ImageAdapter imageAdapter;

    @Transactional
    public void updateImage(Long id, ProjectSimpleDto projectSimpleDto) {
        Project project = projectAdapter.findById(id).get();
        imageAdapter.deleteByProject(project); //기존 사진 모두 삭제
        if(projectSimpleDto.getImages() != null) {
            List<Image> images = projectSimpleDto.getImages().stream()
                    .map(imageSimpleDto -> new Image(project, imageSimpleDto.getName(), imageSimpleDto.getSaved()))
                    .collect(Collectors.toList());
            imageAdapter.saveAll(images);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectAdapter.findById(id).get();
        imageAdapter.deleteByProject(project);
    }
}
