package likelion.univ.alarm.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteRecruitUsecase {

    private final RecruitService recruitService;

    public void execute(long recruitId) {
        recruitService.delete(recruitId);
    }
}
