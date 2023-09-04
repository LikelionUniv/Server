package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public List<User> findUsersByUniversityId(Long id){
        return userRepository.findByUniversityInfoUniversityId(id);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }
    public Page<User> findAllUser(int pageNum){
        return userRepository.findAll(PageRequest.of(pageNum,10, Sort.by("profile.name").ascending()));
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
}
