package likelion.univ.security;

import likelion.univ.exception.NotAuthenticatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtils {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
            && authentication.getPrincipal() instanceof Long) {
            return (Long) authentication.getPrincipal();
        } else {
            throw new NotAuthenticatedException();
        }
    }
}
