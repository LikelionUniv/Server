package likelion.univ.domain.hackathon.repository;

import likelion.univ.domain.hackathon.entity.Hackathon;
import likelion.univ.domain.hackathon.exception.HackathonNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HackathonRepository extends JpaRepository<Hackathon, Long> {

    default Hackathon getById(Long id) {
        return findById(id).orElseThrow(HackathonNotFoundException::new);
    }
}
