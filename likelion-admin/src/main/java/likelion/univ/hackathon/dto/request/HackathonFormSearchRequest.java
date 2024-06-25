package likelion.univ.hackathon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import likelion.univ.domain.hackathon.repository.condition.HackathonFormSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;

// 추후 레코드로 변경하기
@Getter
@AllArgsConstructor
public class HackathonFormSearchRequest {
    @Schema(description = "검색 키워드", example = "김멋사")
    String keyword;

    @NotNull(message = "엑셀 다운로드 여부가 비었습니다.")
    @Schema(description = "엑셀 다운로드 여부", example = "true")
    Boolean isExcelData;

    public HackathonFormSearchCondition toCondition() {
        return new HackathonFormSearchCondition(
                keyword,
                isExcelData
        );
    }
}