package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {
    Optional<User> findByAuthInfoEmail(String email);
    Boolean existsByAuthInfoEmail(String email);
    Page<User> findByUniversityInfoUniversityId(Long univId,Pageable pageable);

    @Query("SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);
}
