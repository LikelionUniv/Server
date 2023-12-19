package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {
    Optional<User> findByAuthInfoEmail(String email);
    Boolean existsByAuthInfoEmail(String email);
    Slice<User> findByUniversityInfoUniversityId(Long univId, Pageable pageable);

    @Query("SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);

    List<User> findByAuthInfoRoleIn(List<Role> roles);
}
