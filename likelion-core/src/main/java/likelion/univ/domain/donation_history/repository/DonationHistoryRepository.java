package likelion.univ.domain.donation_history.repository;

import likelion.univ.domain.donation_history.entity.DonationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationHistoryRepository extends JpaRepository<DonationHistory,Long>, DonationHistoryCustomRepository {
}
