package likelion.univ.donationhistory.dto.response;

import java.time.LocalDate;
import likelion.univ.domain.donationhistory.entity.DonationHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DonationHistoriesSearchResponseDto {

    private Long donationHistoryId;
    private Long authorId;
    private String authorName;
    private String authorProfileImage;
    private String title;
    private LocalDate createdDate;
    private Long viewCount;

    public static DonationHistoriesSearchResponseDto of(DonationHistory donationHistory) {
        return DonationHistoriesSearchResponseDto.builder()
                .donationHistoryId(donationHistory.getId())
                .authorId(donationHistory.getAuthor().getId())
                .authorName(donationHistory.getAuthor().getProfile().getName())
                .authorProfileImage(donationHistory.getAuthor().getProfile().getProfileImage())
                .title(donationHistory.getTitle())
                .createdDate(donationHistory.getCreatedDate().toLocalDate())
                .viewCount(donationHistory.getViewCount())
                .build();
    }
}
