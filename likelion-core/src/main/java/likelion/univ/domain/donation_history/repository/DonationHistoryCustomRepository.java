package likelion.univ.domain.donation_history.repository;

import likelion.univ.domain.donation_history.entity.DonationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationHistoryCustomRepository {
    Page<DonationHistory> searchDonationHistoryWithSort(Long userId, Pageable pageable, String sort, String search);
}
