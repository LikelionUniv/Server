package likelion.univ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LikelionAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionAdminApplication.class, args);
	}

}
