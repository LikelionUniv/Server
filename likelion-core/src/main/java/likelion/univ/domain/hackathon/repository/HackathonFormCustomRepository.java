package likelion.univ.domain.hackathon.repository;

import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.repository.condition.HackathonFormSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HackathonFormCustomRepository {

    Page<HackathonForm> search(HackathonFormSearchCondition condition, Pageable pageable);
}
