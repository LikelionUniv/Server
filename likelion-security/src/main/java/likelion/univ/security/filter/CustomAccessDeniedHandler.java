package likelion.univ.security.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import likelion.univ.exception.AccessDeniedRequestException;
import likelion.univ.security.FilterExceptionProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final FilterExceptionProcessor filterExceptionProcessor;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        /* 예외처리를 커스터마이징 하는 용도 */
        filterExceptionProcessor.excute(response, new AccessDeniedRequestException());
    }
}
