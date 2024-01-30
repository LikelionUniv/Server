package likelion.univ.domain.user.service;

import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.*;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;

    private final UserRepository userRepository;
    private final UniversityAdaptor universityAdaptor;


    public User login(LoginType loginType, String email){
        User user = userAdaptor.findByEmail(email);
        if(user.getAuthInfo().getLoginType().equals(loginType)){
            return user;
        }else throw new EmailAlreadyRegistered();
    }

    @Transactional
    public User signUp(Profile profile, AuthInfo authInfo, UniversityInfo universityInfo){
        User user = User.builder()
                .profile(profile)                .authInfo(authInfo)
                .universityInfo(universityInfo)
                .build();
        return userAdaptor.save(user);
    }
    @Transactional
    public User editProfile(Long userId, Profile profile){
        User user = userAdaptor.findById(userId);
        user.editProfile(profile);
        return user;
    }


    @Transactional
    public void updateUser(User user, String name, String part,
                           String major, Long ordinal, Role role){

        user.getProfile().updateProfile(name,Part.valueOf(part));
        user.getUniversityInfo().updateUniversityInfo(major,ordinal);
        user.getAuthInfo().updateRole(role);

        userAdaptor.save(user);
    }

    @Transactional
    public void deleteUser(User user){
        user.getAuthInfo().delete();
        user.getProfile().delete();

        userAdaptor.save(user);
    }

    @Transactional
    public void deleteAll(List<User> users){
        users.stream().forEach(user ->{
            user.getAuthInfo().delete();
            user.getProfile().delete();
            userAdaptor.save(user);
        });
    }


    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return userAdaptor.findDynamicUsers(condition);
    }

    public List<User> findByRoleIn(List<Role> roles) {
        return userRepository.findByAuthInfoRoleIn(roles);
    }
}
