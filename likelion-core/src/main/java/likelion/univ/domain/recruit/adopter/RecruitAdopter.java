package likelion.univ.domain.recruit.adopter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.repository.RecruitRepository;
import likelion.univ.exception.GlobalErrorCode;
import likelion.univ.exception.base.BaseException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class RecruitAdopter {

    private final RecruitRepository recruitRepository;

    public Long save(Recruit recruit) {
        return recruitRepository.save(recruit).getId();
    }

    public Recruit findById(Long id) {
        return recruitRepository.findById(id)
                .orElseThrow(() -> new BaseException(GlobalErrorCode.BAD_REQUEST_ERROR)); // TODO ERROR CODE 수정 필요
    }

    public List<Recruit> findAllByUniversityId(Long universityId) {
        return recruitRepository.findAllByUniversityIdAndGeneration(universityId, 12);
    }
}
