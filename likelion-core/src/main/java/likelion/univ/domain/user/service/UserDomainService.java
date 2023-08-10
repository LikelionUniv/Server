package likelion.univ.domain.user.service;

import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDomainService {

    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public void updateUser(User user, String name,
                                          String major,String email,
                                          Long ordinal){

       user.updateProfile(new Profile(name,email,user.getProfile().getProfileImage(),user.getProfile().getIntroduction(),user.getProfile().getPart()));
       user.updateUniversityInfo(new UniversityInfo(user.getUniversityInfo().getUniversity(),major,ordinal));

       userRepository.save(user);
    }

}
