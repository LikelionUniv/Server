package likelion.univ.domain.recruit.service;

import likelion.univ.domain.recruit.adopter.RecruitAdopter;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitAdopter recruitAdopter;

    public Long register(Recruit recruit) {
        return recruitAdopter.save(recruit);
    }
}
