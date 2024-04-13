package likelion.univ.domain.donationhistory.service;

import likelion.univ.domain.donationhistory.entity.DonationHistory;
import likelion.univ.domain.donationhistory.repository.DonationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonationHistoryService {

    private final DonationHistoryRepository donationHistoryRepository;

    @Transactional
    public DonationHistory getAndViewCountUp(Long donationHistoryId) {
        DonationHistory donationHistory = donationHistoryRepository.getById(donationHistoryId);
        donationHistory.viewCountUp();
        return donationHistory;
    }
}
