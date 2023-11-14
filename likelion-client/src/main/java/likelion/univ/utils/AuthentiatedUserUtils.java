package likelion.univ.utils;

import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.UserNotMatchException;
import likelion.univ.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthentiatedUserUtils {
    private final UserAdaptor userAdaptor;

    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }

    public User getCurrentUser() {
        return userAdaptor.findById(getCurrentUserId());
    }

    public void checkidentification(Long userId){
        if(!SecurityUtils.getCurrentUserId().equals(userId))
            throw new UserNotMatchException();
    }
}
