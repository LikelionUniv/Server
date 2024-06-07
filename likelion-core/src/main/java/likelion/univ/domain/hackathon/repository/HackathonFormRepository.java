package likelion.univ.domain.hackathon.repository;

import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.exception.HackathonFormNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackathonFormRepository extends JpaRepository<HackathonForm, Long> {

    default HackathonForm getById(Long id) {
        return findById(id).orElseThrow(HackathonFormNotFoundException::new);
    }
}
