package likelion.univ.donation_history.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import likelion.univ.domain.donation_history.service.DonationHistoryDomainService;
import likelion.univ.donation_history.dto.response.DonationHistoriesDetailsResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetDonationHistoryDetailsUseCase {
    private final DonationHistoryDomainService donationHistoryDomainService;

    public DonationHistoriesDetailsResponseDto execute(Long donationHistoryId){
        DonationHistory donationHistory = donationHistoryDomainService.getAndViewCountUp(donationHistoryId);
        /* 임시 (유저 도메인 배포 후 변경 예정) */
        return DonationHistoriesDetailsResponseDto.of(donationHistory,false);
    }
}
