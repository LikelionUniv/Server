package likelion.univ.domain.donationhistory.repository;

import likelion.univ.domain.donationhistory.entity.DonationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DonationHistoryCustomRepository {

    Page<DonationHistory> searchDonationHistoryWithSort(Pageable pageable, String sort, String search);
}
