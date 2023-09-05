package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAuthInfoEmail(String email);
    Boolean existsByAuthInfoEmail(String email);
    List<User> findByUniversityInfoUniversityId(Long univId);
    Page<User> findAll(Pageable pageable);
}
