package likelion.univ.domain.project.repository;

import likelion.univ.domain.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectCustomRepository {

    Page<Project> findByOrdinal(Long ordinal, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.ordinal <= :ordinal ORDER BY p.ordinal DESC")
    Page<Project> findArchivePosts(@Param("ordinal") Long ordinal, Pageable pageable);

    @Query("SELECT MAX(p.ordinal) FROM Project p")
    int findLatestOrdinal();
}