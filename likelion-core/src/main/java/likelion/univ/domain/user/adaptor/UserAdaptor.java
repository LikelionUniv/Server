package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import likelion.univ.domain.user.repository.UserQueryRepository;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    private final UserQueryRepository userQueryRepository;
    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException());
    }
    public User findByEmail(String email){
        return userRepository.findByAuthInfoEmail(email)
                .orElseThrow(()-> new UserNotFoundException());
    }
    public Boolean checkEmail(String email){
        return userRepository.existsByAuthInfoEmail(email);
    }
    public User save(User user){
        return userRepository.save(user);
    }

    public Page<User> findAllUser(int pageNum){
        return userRepository.findAll(PageRequest.of(pageNum,10, Sort.by("profile.name").ascending()));
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public List<User> findUsersByUniversityId(Long id) {
        return userRepository.findByUniversityInfoUniversityId(id);
    }

    public List<User> findAllByUniversityName(String univName) {
        return userRepository.findByUniversityInfoUniversityName(univName);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return userQueryRepository.findDynamicUsers(condition);
    }
}
