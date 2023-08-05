package likelion.univ.domain.project.service;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.dto.ProjectSimpleDto;
import likelion.univ.domain.project.repository.ImageRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ImageService {

    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;
    @Transactional
    public void updateImage(Long id, ProjectSimpleDto projectSimpleDto) {
        Project project = projectRepository.findById(id).get();
        imageRepository.deleteByProject(project); //기존 사진 모두 삭제
        if(projectSimpleDto.getImages() != null) {
            List<Image> images = projectSimpleDto.getImages().stream()
                    .map(imageSimpleDto -> new Image(project, imageSimpleDto.getName(), imageSimpleDto.getSaved()))
                    .collect(Collectors.toList());
            imageRepository.saveAll(images);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        Project project = projectRepository.findById(id).get();
        imageRepository.deleteByProject(project);
    }
}
