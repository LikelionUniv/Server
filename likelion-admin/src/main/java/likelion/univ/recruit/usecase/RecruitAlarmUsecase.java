package likelion.univ.recruit.usecase;

import likelion.univ.recruit.dto.RecruitAlarmContentDto;

public interface RecruitAlarmUsecase {
    void execute(RecruitAlarmContentDto recruitAlarmContentDto, Long universityId);
}
