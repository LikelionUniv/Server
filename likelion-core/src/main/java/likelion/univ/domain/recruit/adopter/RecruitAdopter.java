package likelion.univ.domain.recruit.adopter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.exception.RecruitNotFound;
import likelion.univ.domain.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecruitAdopter {
    private final RecruitRepository recruitRepository;

    public Long save(Recruit recruit) {
        return recruitRepository.save(recruit).getId();
    }

    public Recruit findById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(RecruitNotFound::new);
    }
}
