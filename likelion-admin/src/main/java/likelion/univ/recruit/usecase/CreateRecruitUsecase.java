package likelion.univ.recruit.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitService;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.service.UniversityService;
import likelion.univ.recruit.dto.RecruitRegisterDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateRecruitUsecase {

    private final RecruitService recruitService;
    private final UniversityService universityService;

    public Long execute(RecruitRegisterDto recruitRegisterDto, String universityName) {
        University university = universityService.findByName(universityName);
        Recruit recruit = recruitRegisterDto.map(university);

        return recruitService.register(recruit);
    }
}
