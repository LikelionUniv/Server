package likelion.univ.domain.user.service;

import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.*;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
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
                .profile(profile)
                .authInfo(authInfo)
                .universityInfo(universityInfo)
                .build();
        return userAdaptor.save(user);
    }

    public User findByEmail(String email) {
        return userAdaptor.findByEmail(email);
    }

    public List<User> findAll(UserSearchCondition condition) {
        return findAll(condition);
    }

}
