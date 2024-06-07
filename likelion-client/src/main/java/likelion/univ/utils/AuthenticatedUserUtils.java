package likelion.univ.utils;

import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotMatchException;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO 이것도 반드시 제거..
@Component
@RequiredArgsConstructor
public class AuthenticatedUserUtils {

    private final UserRepository userRepository;

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

    public User getCurrentUser() {
        return userRepository.getById(getCurrentUserId());
    }

    public void checkIdentification(Long userId) {
        if (!SecurityUtils.getCurrentUserId().equals(userId)) {
            throw new UserNotMatchException();
        }
    }
}
