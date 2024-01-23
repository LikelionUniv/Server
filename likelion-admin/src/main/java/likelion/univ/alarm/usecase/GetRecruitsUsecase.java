package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.GetRecruitsDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.entity.Recruit;
import likelion.univ.domain.recruit.service.RecruitQueryService;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetRecruitsUsecase {

    private final RecruitQueryService recruitQueryService;
    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserAdaptor userAdaptor;
    public GetRecruitsDto execute(int generation) {
        Long userId = authenticatedUserUtils.getCurrentUserId();
        University university = userAdaptor.findByIdWithUniversity(userId).getUniversityInfo().getUniversity();

        List<Recruit> recruits = recruitQueryService
                .queryAllByUniversityNameAndGeneration(university.getName(), generation);

        return new GetRecruitsDto(university.getName(), recruits);
    }
}
