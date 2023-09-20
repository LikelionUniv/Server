package likelion.univ.recruit.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.recruit.dto.RecruitAlarmContent;

public interface RecruitAlarmUsecase {
    void execute(RecruitAlarmContent recruitAlarmContent, Long universityId);
}
