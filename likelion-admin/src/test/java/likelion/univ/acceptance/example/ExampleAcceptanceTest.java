package likelion.univ.acceptance.example;

import static likelion.univ.acceptance.example.ExampleAcceptanceSteps.예시_생성_요청;
import static likelion.univ.acceptance.example.ExampleAcceptanceSteps.예시_수정_요청;
import static org.assertj.core.api.Assertions.assertThat;

import likelion.univ.acceptance.AcceptanceTest;
import likelion.univ.domain.example.entity.Example;
import likelion.univ.domain.example.repository.ExampleRepository;
import likelion.univ.example.dto.request.CreateExampleRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@DisplayName("인수테스트 예시")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
public class ExampleAcceptanceTest extends AcceptanceTest {

    @Autowired
    private ExampleRepository exampleRepository;

    @Nested
    class 예시_생성_API {

        @Test
        void 예시를_생성한다() {
            // given
            var request = new CreateExampleRequestDto("example");

            // when
            var response = 예시_생성_요청(request);

            // then
            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }

        @Test
        void 예시를_수정한다() {
            // given
            /*
              참고 - 생성 시 Location 헤더에 /v1/example/{생성된 예시 ID} 식으로 id값을 반환한다면 아래와 같이 사용할 수 있음
              var request = new CreateExampleRequestDto("example");
              Long exampleId = ID_추출(예시_생성_요청(request));
             */
            Long exampleId = exampleRepository.save(new Example("sample", "sample"))
                    .getId();
            var request = new CreateExampleRequestDto("update");

            // when
            var response = 예시_수정_요청(request, exampleId);

            // then
            assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        }
    }
}
