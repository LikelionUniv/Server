package likelion.univ.domain.user.service;

import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.*;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.exception.NoDataFound;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<User> findAllUser(int pageNum){
        Page<User> pageUsers = userAdaptor.findAllUser(pageNum);
        List<User> pageToListUsers = new ArrayList<User>();

        if(pageUsers!=null && pageUsers.hasContent()){
            return pageToListUsers = pageUsers.getContent();
        }else throw new NoDataFound();
    }

    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return userAdaptor.findDynamicUsers(condition);
    }
}
