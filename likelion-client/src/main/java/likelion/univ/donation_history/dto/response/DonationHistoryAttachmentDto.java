package likelion.univ.donation_history.dto.response;

import likelion.univ.domain.donation_history.entity.DonationHistoryAttachment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DonationHistoryAttachmentDto {

    private String fileName;
    private String fileExtension;
    private String fileUrl;

    public static DonationHistoryAttachmentDto of(DonationHistoryAttachment donationHistoryAttachment) {
        return DonationHistoryAttachmentDto.builder()
                .fileName(donationHistoryAttachment.getFileName())
                .fileExtension(donationHistoryAttachment.getFileExtension())
                .fileUrl(donationHistoryAttachment.getFileUrl())
                .build();
    }
}
