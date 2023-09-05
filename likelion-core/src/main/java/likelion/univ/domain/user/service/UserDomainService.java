package likelion.univ.domain.user.service;

import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.*;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {
    private final UserAdaptor userAdaptor;

    public User login(LoginType loginType, String email){
        User user = userAdaptor.findByEmail(email);
        if(user.getAuthInfo().getLoginType().equals(loginType)){
            return user;
        }else throw new EmailAlreadyRegistered();
    }

    @Transactional
    public User signUp(Profile profile, AuthInfo authInfo, UniversityInfo universityInfo){
        User user = User.builder()
                .profile(profile)
                .authInfo(authInfo)
                .universityInfo(universityInfo)
                .build();
        return userAdaptor.save(user);
    }

    @Transactional
    public void updateUser(User user, String name, String part,
                           String major, Long ordinal){

        user.getProfile().updateProfile(name,Part.valueOf(part));
        user.getUniversityInfo().updateUniversityInfo(major,ordinal);


        userAdaptor.save(user);
    }

    @Transactional
    public void deleteUser(User user){
        userAdaptor.delete(user);
    }

}
