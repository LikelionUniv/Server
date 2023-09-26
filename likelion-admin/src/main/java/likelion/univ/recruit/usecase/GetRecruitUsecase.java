package likelion.univ.recruit.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetRecruitUsecase {

    private final RecruitQueryService recruitQueryService;

    public void execute(String universityName) {
        List<Recruit> recruits = recruitQueryService.queryAllByUniversityName(universityName);
    }
}
