package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    public List<Image> findByProject(Project project);
    public void deleteByProject(Project project);
}
