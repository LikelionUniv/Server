package likelion.univ.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    /**
     * Beanstalk에서 healthcheck 용도로 사용
     */
    @GetMapping("/check")
    public void healthCheck() {
    }
}
