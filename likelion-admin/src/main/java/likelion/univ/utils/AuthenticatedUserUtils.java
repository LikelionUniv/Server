package likelion.univ.utils;

import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// TODO 이거 반드시 지우기.. 서비스 layer 단에서 절대 시큐리티 쓰지 말고, 프레젠테이션에서 Id로 넘겨주는걸로..
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
}
