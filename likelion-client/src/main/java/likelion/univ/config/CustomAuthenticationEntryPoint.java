package likelion.univ.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import likelion.univ.exception.SecurityErrorCode;
import likelion.univ.response.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // Error Response Body
        PrintWriter writer = response.getWriter();
        ErrorResponse errorDetails = ErrorResponse.of(SecurityErrorCode.NOT_AUTHENTIATED_ERROR);
        new ObjectMapper().writeValue(writer, errorDetails);
        writer.flush();
    }
}
