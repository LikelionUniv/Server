package likelion.univ.donationhistory.service;

import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.donationhistory.entity.DonationHistory;
import likelion.univ.domain.donationhistory.repository.DonationHistoryRepository;
import likelion.univ.domain.donationhistory.service.DonationHistoryDomainService;
import likelion.univ.donationhistory.dto.response.DonationHistoriesDetailsResponseDto;
import likelion.univ.donationhistory.dto.response.DonationHistoriesSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DonationHistoryService {

    private final DonationHistoryRepository donationHistoryRepository;
    private final DonationHistoryDomainService donationHistoryDomainService;

    public DonationHistoriesDetailsResponseDto getDetails(Long donationHistoryId) {
        DonationHistory donationHistory = donationHistoryDomainService.getAndViewCountUp(donationHistoryId);
        /* 임시 (유저 도메인 배포 후 변경 예정) */
        return DonationHistoriesDetailsResponseDto.of(donationHistory, false);
    }

    public PageResponse<DonationHistoriesSearchResponseDto> search(String sort, String search, Pageable pageable) {
        return PageResponse.of(donationHistoryRepository
                .searchDonationHistoryWithSort(pageable, sort, search)
                .map(DonationHistoriesSearchResponseDto::of)
        );
    }
}
