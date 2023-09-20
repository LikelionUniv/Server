package likelion.univ.recruit.controller.enums;

import likelion.univ.recruit.usecase.EmailRecruitAlarmUsecase;
import likelion.univ.recruit.usecase.RecruitAlarmUsecase;
import likelion.univ.recruit.usecase.SmsRecruitAlarmUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.function.Supplier;

@RequiredArgsConstructor
public enum AlarmType {
    EMAIL(() -> {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean("emailRecruitAlarmUsecase", EmailRecruitAlarmUsecase.class);
    }),
    SMS(() -> {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean("smsRecruitAlarmUsecase", SmsRecruitAlarmUsecase.class);
    });

    private final Supplier<RecruitAlarmUsecase> sender;

    public static RecruitAlarmUsecase of(String type) {
        if (Arrays.stream(values()).count() == 0 ) {
            throw new IllegalStateException("일치하는 타입이 없음");
        }
        return valueOf(type.toUpperCase()).supplyUseCases();
    }

    private RecruitAlarmUsecase supplyUseCases() {
        return sender.get();
    }

}
