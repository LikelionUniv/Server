package likelion.univ.domain.user.repository;

import java.util.List;
import java.util.Optional;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

    default User getById(Long id) {
        return findById(id).orElseThrow(UserNotFoundException::new);
    }

    default User getByIdWithUniversity(Long id) {
        return findByIdWithUniversity(id).orElseThrow(UserNotFoundException::new);
    }

    default User getByEmail(String email) {
        return findByAuthInfoEmail(email).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findByAuthInfoEmail(String email);

    boolean existsByAuthInfoEmail(String email);

    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);

    List<User> findByAuthInfoRoleIn(List<Role> roles);

    default List<User> findAllByIdsExactly(List<Long> ids) {
        List<User> users = findAllByIdIn(ids);
        if (users.size() != ids.size()) {
            throw new UserNotFoundException();
        }
        return users;
    }

    List<User> findAllByIdIn(List<Long> ids);
}
