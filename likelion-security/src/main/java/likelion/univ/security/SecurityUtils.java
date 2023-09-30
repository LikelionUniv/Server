package likelion.univ.security;

import likelion.univ.exception.NotAuthentiatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils(){};
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /*
        System.out.println("authentication = " + authentication);
        System.out.println("authentication.isAuthenticated() = " + authentication.isAuthenticated());
        System.out.println("authentication.getPrincipal().toString() = " + authentication.getPrincipal().toString());
        */

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Long) {
            return (Long) authentication.getPrincipal();
        }
        else throw new NotAuthentiatedException();
    }
}
