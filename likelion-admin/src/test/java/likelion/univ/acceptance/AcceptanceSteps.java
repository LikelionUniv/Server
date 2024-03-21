package likelion.univ.acceptance;

import static io.restassured.http.ContentType.JSON;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings("NonAsciiCharacters")
public final class AcceptanceSteps {

    public static RequestSpecification given() {
        return RestAssured
                .given()
                .contentType(JSON);
    }

    public static RequestSpecification given(String token) {
        return RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(JSON);
    }
}
