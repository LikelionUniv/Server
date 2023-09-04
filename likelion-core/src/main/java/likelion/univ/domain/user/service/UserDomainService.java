package likelion.univ.domain.user.service;

import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {

    private final UserAdaptor userAdaptor;


    @Transactional
    public void updateUser(User user, String name, String part,
                           String major, String email,
                           Long ordinal){

        user.getProfile().updateProfile(name,email,Part.valueOf(part));
        user.getUniversityInfo().updateUniversityInfo(major,ordinal);


        userAdaptor.saveUser(user);
    }

    @Transactional
    public void deleteUser(User user){
        userAdaptor.deleteUser(user);
    }

}
