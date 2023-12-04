package likelion.univ.domain.donation_history.repository;

import likelion.univ.domain.donation_history.entity.DonationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DonationHistoryRepository extends JpaRepository<DonationHistory,Long>, DonationHistoryCustomRepository {
    @Query("select distinct d from DonationHistory d join fetch d.author join fetch d.attachments where d.id = :id")
    Optional<DonationHistory> findById(Long id);
}
