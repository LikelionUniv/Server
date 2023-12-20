package likelion.univ.domain.recruit.service;

import likelion.univ.domain.recruit.adopter.RecruitAdopter;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.exception.EmailAlreadyRegistered;
import likelion.univ.domain.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitAdopter recruitAdopter;
    private final RecruitRepository recruitRepository;

    public Long register(Recruit recruit) {
        if (recruitRepository.existsByEmailAndGeneration(recruit.getEmail(), recruit.getGeneration())) {
            throw new EmailAlreadyRegistered();
        }
        return recruitAdopter.save(recruit);
    }
}
