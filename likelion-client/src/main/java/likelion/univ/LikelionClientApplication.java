package likelion.univ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LikelionClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionClientApplication.class, args);
	}

}
