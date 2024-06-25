package likelion.univ.domain.hackathon.repository.condition;

public record HackathonFormSearchCondition(
        String keyword,
        boolean isExcelData
) {
}
