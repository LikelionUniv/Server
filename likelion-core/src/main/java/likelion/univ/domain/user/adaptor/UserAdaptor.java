package likelion.univ.domain.user.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UserAdaptor {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
