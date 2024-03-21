package likelion.univ.acceptance;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import likelion.univ.LikelionAdminApplication;
import likelion.univ.support.DataClearExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@ExtendWith(DataClearExtension.class)  // DB 데이터를 매 테스트별로 지움으로써 테스트 간 격리
@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = LikelionAdminApplication.class)
public abstract class AcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    protected void setUp() {
        RestAssured.port = port;
    }
}
