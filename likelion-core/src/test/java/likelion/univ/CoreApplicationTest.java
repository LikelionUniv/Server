package likelion.univ;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplicationTest.class, args);
    }

    @Test
    void ContextLoads() {

    }
}
