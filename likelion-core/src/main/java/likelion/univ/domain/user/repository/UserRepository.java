package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.LoginType;
import likelion.univ.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

    Optional<User> findByLoginTypeAndOid(LoginType loginType, String oid);
}
