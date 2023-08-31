package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ImageAdaptor {

    private final ImageRepository imageRepository;

    public List<Image> findByProject(Project project) {
        return imageRepository.findByProject(project);
    }

    public void saveAll(List<Image> images) {
        imageRepository.saveAll(images);
    }

    public void deleteByProject(Project project) {
        imageRepository.deleteByProject(project);
    }

    public void save(Image image){
        imageRepository.save(image);
    }
}
