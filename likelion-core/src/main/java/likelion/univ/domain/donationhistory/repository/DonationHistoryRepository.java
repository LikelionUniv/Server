package likelion.univ.domain.donationhistory.repository;

import likelion.univ.domain.donationhistory.entity.DonationHistory;
import likelion.univ.domain.donationhistory.exception.DonationHistoryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationHistoryRepository extends
        JpaRepository<DonationHistory, Long>,
        DonationHistoryCustomRepository {

    default DonationHistory getById(Long id) {
        return findById(id).orElseThrow(DonationHistoryNotFoundException::new);
    }
}
