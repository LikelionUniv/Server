package likelion.univ.recruit.service;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitService;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.service.UniversityService;
import likelion.univ.recruit.dto.CreateRecruitDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ClientRecruitService {

    private final RecruitService recruitService;
    private final UniversityService universityService;

    public Long create(CreateRecruitDto createRecruitDto, String universityName) {
        University university = universityService.findByName(universityName);
        Recruit recruit = createRecruitDto.toEntity(university);
        return recruitService.register(recruit);
    }
}
