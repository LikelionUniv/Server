package likelion.univ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LikelionAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionAdminApplication.class, args);
	}

}
