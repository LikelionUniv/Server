package likelion.univ.domain.user.repository;

import likelion.univ.domain.user.entity.Role;
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
    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university " +
            "where u.universityInfo.university.id = :univId and u.authInfo.accountStatus= 'ACTIVE' ",
            countQuery = "SELECT count(u.id) FROM User u join u.universityInfo.university " +
                    "where u.universityInfo.university.id = :univId and u.authInfo.accountStatus= 'ACTIVE'")
    Page<User> findByUniversityInfoUniversityId(Long univId, Pageable pageable);
    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university " +
            "where u.universityInfo.university.name like :univName% and u.authInfo.role = :role " +
            "and u.authInfo.accountStatus= 'ACTIVE'",
            countQuery = "SELECT count(u.id) FROM User u join u.universityInfo.university " +
                    "where u.universityInfo.university.name like :univName% and u.authInfo.role = :role " +
                    "and u.authInfo.accountStatus= 'ACTIVE'")
    Page<User> findByUnivNameAndRole(Role role, String univName, Pageable pageable);
    @Query(value = "SELECT u FROM User u " +
            "where u.authInfo.accountStatus= 'ACTIVE'",
            countQuery = "SELECT count(u.id) FROM User u  " +
                    "where u.authInfo.accountStatus= 'ACTIVE'")
    Page<User> findAll(Pageable pageable);
    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university " +
            "where u.universityInfo.university.name like :univName% and u.authInfo.accountStatus= 'ACTIVE'",
            countQuery = "SELECT count(u.id) FROM User u join u.universityInfo.university " +
                    "where u.universityInfo.university.name like :univName% and u.authInfo.accountStatus= 'ACTIVE'")
    Page<User> findByUniversityInfoUniversityName(String univName, Pageable pageable);
    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university " +
            "where u.authInfo.role = :role and u.authInfo.accountStatus= 'ACTIVE'",
            countQuery = "SELECT count(u.id) FROM User u join u.universityInfo.university " +
                    "where  u.authInfo.role = :role and u.authInfo.accountStatus= 'ACTIVE'")
    Page<User> findByAuthInfoRole(Role role, Pageable pageable);


    @Query(value = "SELECT u FROM User u join fetch u.universityInfo.university where u.id = :id ")
    Optional<User> findByIdWithUniversity(Long id);

    List<User> findByAuthInfoRoleIn(List<Role> roles);
    List<User> findAllByIdIn(List<Long> ids);
}
