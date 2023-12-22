package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.GetRecruitsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetRecruitsUsecase {

    private final RecruitQueryService recruitQueryService;

    public GetRecruitsDto execute(User user, int generation) {
        University university = user.getUniversityInfo().getUniversity();
        List<Recruit> recruits = recruitQueryService
                .queryAllByUniversityNameAndGeneration(university.getName(), generation);

        return new GetRecruitsDto(university.getName(), recruits);
    }
}
