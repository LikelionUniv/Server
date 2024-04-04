package likelion.univ.domain.donation_history.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import likelion.univ.domain.donation_history.exception.DonationHistoryNotFoundException;
import likelion.univ.domain.donation_history.repository.DonationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Adaptor
@RequiredArgsConstructor
public class DonationHistoryAdaptor {

    private final DonationHistoryRepository donationHistoryRepository;

    public DonationHistory findById(Long id) {
        return donationHistoryRepository.findById(id).orElseThrow(() -> new DonationHistoryNotFoundException());
    }

    public Page<DonationHistory> searchDonationHistoryWithSort(Pageable pageable, String sort, String search) {
        return donationHistoryRepository.searchDonationHistoryWithSort(pageable, sort, search);
    }
}
