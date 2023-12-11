package likelion.univ.domain.project.repository;


import likelion.univ.domain.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectCustomRepository {
    Page<Project> findByProjectMember(Long userId, Pageable pageable);
}
