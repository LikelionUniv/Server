package likelion.univ.domain.user.repository;

import java.util.List;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserCustomRepository {

    List<User> findDynamicUsers(UserSearchCondition condition);

    Slice<User> findFollowingUsersByFollowerID(Long userId, Pageable pageable);

    Slice<User> findFollowerUsersByFollowingID(Long userId, Pageable pageable);

    Slice<User> searchByName(String name, Pageable pageable);

    List<User> findMyFollowingUsersByFollowingIdIn(Long followerId, List<Long> followingIdList);

    Page<User> findAllWithUniversity(Pageable pageable);

    Page<User> findByUnivNameAndRole(Role role, String univName, Pageable pageable);

    Page<User> findByUniversityInfoUniversityId(Long univId, Pageable pageable);
}
