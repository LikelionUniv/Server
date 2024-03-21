package likelion.univ.acceptance.example;

import static likelion.univ.acceptance.AcceptanceSteps.given;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import likelion.univ.example.dto.request.CreateExampleRequestDto;

@SuppressWarnings("NonAsciiCharacters")
public class ExampleAcceptanceSteps {

    public static ExtractableResponse<Response> 예시_생성_요청(CreateExampleRequestDto request) {
        return given()
                .body(request)
                .post("/api/admin/v1/example")
                .then()
                .extract();
    }

    public static ExtractableResponse<Response> 예시_수정_요청(
            CreateExampleRequestDto request,
            Long exampleId
    ) {
        return given()
                .body(request)
                .patch("/api/admin/v1/example/{exampleId}", exampleId)
                .then()
                .extract();
    }
}
