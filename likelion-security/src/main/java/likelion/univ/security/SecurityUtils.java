package likelion.univ.security;

import likelion.univ.exception.NotAuthentiatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class SecurityUtils {
    private SecurityUtils(){};
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // for test
        System.out.println("authentication = " + authentication);
        System.out.println("authentication.isAuthenticated() = " + authentication.isAuthenticated());
        System.out.println("authentication.getPrincipal().toString() = " + authentication.getPrincipal().toString());

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Long) {
            System.out.println("로그인 성공");
            return (Long) authentication.getPrincipal();
        } else {
            throw new NotAuthentiatedException();
        }
    }
}
