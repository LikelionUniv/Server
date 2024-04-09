package likelion.univ.donation_history.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.donationhistory.entity.DonationHistory;
import likelion.univ.domain.donationhistory.repository.DonationHistoryRepository;
import likelion.univ.donation_history.dto.response.DonationHistoriesSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class SearchDonationHistoryUsecase {

    private final DonationHistoryRepository donationHistoryRepository;

    public PageResponse<DonationHistoriesSearchResponseDto> execute(String sort, String search, Pageable pageable) {
        Page<DonationHistory> donationHistories = donationHistoryRepository.searchDonationHistoryWithSort(
                pageable, sort, search
        );
        return PageResponse.of(donationHistories.map(DonationHistoriesSearchResponseDto::of));
    }
}
