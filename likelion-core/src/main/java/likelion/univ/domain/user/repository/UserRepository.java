package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {
    Optional<User> findByAuthInfoEmail(String email);
    Boolean existsByAuthInfoEmail(String email);
<<<<<<< HEAD
    List<User> findByUniversityInfoUniversityId(Long univId);
    Page<User> findAll(Pageable pageable);
    List<User> findByUniversityInfoUniversityName(String univName);
=======

    @Query("SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
}
