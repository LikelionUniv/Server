package likelion.univ.domain.user.service;

import java.util.List;
import likelion.univ.domain.user.entity.AuthInfo;
import likelion.univ.domain.user.entity.LoginType;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User login(LoginType loginType, String email) {
        User user = userRepository.getByEmail(email);
        if (user.getAuthInfo().getLoginType().equals(loginType)) {
            return user;
        } else {
            throw new EmailAlreadyRegistered();
        }
    }

    @Transactional
    public User signUp(Profile profile, AuthInfo authInfo, UniversityInfo universityInfo) {
        User user = User.builder()
                .profile(profile)
                .authInfo(authInfo)
                .universityInfo(universityInfo)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User editProfile(Long userId, Profile profile) {
        User user = userRepository.getById(userId);
        user.editProfile(profile);
        return user;
    }

    @Transactional
    public void updateUser(User user, String name, String part,
                           String major, Long ordinal, Role role) {

        user.getProfile().updateProfile(name, Part.valueOf(part));
        user.getUniversityInfo().updateUniversityInfo(major, ordinal);
        user.getAuthInfo().updateRole(role);

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user) {
        user.getAuthInfo().delete();
        user.getProfile().delete();

        userRepository.save(user);
    }

    @Transactional
    public void deleteAll(List<User> users) {
        users.stream().forEach(user -> {
            user.getAuthInfo().delete();
            user.getProfile().delete();
            userRepository.save(user);
        });
    }

    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return userRepository.search(condition);
    }

    public List<User> findByRoleIn(List<Role> roles) {
        return userRepository.findByAuthInfoRoleIn(roles);
    }
}
