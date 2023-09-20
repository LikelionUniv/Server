package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotFoundException;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
