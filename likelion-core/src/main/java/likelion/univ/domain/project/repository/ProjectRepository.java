package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByOrdinal(Long ordinal, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.ordinal <= :ordinal ORDER BY p.ordinal DESC")
    List<Project> findArchivePosts(@Param("ordinal") Long ordinal);

    @Query("SELECT MAX(p.ordinal) FROM Project p")
    int findLatestOrdinal();

}