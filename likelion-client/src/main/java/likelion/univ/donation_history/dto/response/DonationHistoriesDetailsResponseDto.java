package likelion.univ.donation_history.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DonationHistoriesDetailsResponseDto {

    private Long donationHistoryId;
    private Long authorId;
    private String authorName;
    private String authorProfileImage;
    private String title;
    private String body;
    private Boolean isAuthor;
    private LocalDate createdDate;
    private Long viewCount;
    private List<DonationHistoryAttachmentDto> attachments;

    public static DonationHistoriesDetailsResponseDto of(DonationHistory donationHistory, Boolean isAuthor) {
        return DonationHistoriesDetailsResponseDto.builder()
                .donationHistoryId(donationHistory.getId())
                .authorId(donationHistory.getAuthor().getId())
                .authorName(donationHistory.getAuthor().getProfile().getName())
                .authorProfileImage(donationHistory.getAuthor().getProfile().getProfileImage())
                .title(donationHistory.getTitle())
                .body(donationHistory.getBody())
                .isAuthor(isAuthor)
                .createdDate(donationHistory.getCreatedDate().toLocalDate())
                .viewCount(donationHistory.getViewCount())
                .attachments(donationHistory.getAttachments().stream()
                        .map(a -> DonationHistoryAttachmentDto.of(a)).collect(Collectors.toList()))
                .build();
    }
    /* 유저도메인 배포 후 진행 */
//    public static DonationHistoriesDetailsResponseDto of(DonationHistory donationHistory, Long userId){
//        if(donationHistory.getAuthor().getId().equals(userId))
//            return DonationHistoriesDetailsResponseDto.of(donationHistory,true);
//        else return DonationHistoriesDetailsResponseDto.of(donationHistory, false);
//    }
}
