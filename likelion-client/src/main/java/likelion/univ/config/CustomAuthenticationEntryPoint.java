package likelion.univ.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import likelion.univ.exception.GlobalErrorCode;
import likelion.univ.response.ErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // Error Response Body
        PrintWriter writer = response.getWriter();
        ErrorResponse errorDetails = ErrorResponse.of(GlobalErrorCode.ACCESS_DENIED_REQUEST);
        new ObjectMapper().writeValue(writer, errorDetails);
        writer.flush();
    }
}
