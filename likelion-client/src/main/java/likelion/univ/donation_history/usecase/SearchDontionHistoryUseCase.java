package likelion.univ.donation_history.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.donation_history.adaptor.DonationHistoryAdaptor;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import likelion.univ.donation_history.dto.response.DonationHistoriesSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class SearchDontionHistoryUseCase {
    private final DonationHistoryAdaptor donationHistoryAdaptor;

    public PageResponse<DonationHistoriesSearchResponseDto> execute(String sort, String search, Pageable pageable){
        Page<DonationHistory> donationHistories = donationHistoryAdaptor.searchDonationHistoryWithSort(pageable, sort, search);
        return PageResponse.of(donationHistories.map(d ->DonationHistoriesSearchResponseDto.of(d)));
    }
}
