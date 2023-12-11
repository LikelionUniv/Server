package likelion.univ.domain.donation_history.service;

import likelion.univ.domain.donation_history.adaptor.DonationHistoryAdaptor;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DonationHistoryDomainService {
    private final DonationHistoryAdaptor donationHistoryAdaptor;

    @Transactional
    public DonationHistory getAndViewCountUp(Long donationHistoryId){
        DonationHistory donationHistory = donationHistoryAdaptor.findById(donationHistoryId);
        donationHistory.viewCountUp();
        return donationHistory;
    }
}
