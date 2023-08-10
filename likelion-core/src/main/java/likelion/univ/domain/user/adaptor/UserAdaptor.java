package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.example.exception.ExampleNotFoundException;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public List<User> findUsersByUniversityId(Long id){
        return userRepository.findByUniversityInfoUniversityId(id);
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
