//package likelion.univ.alarm.usecase;
//
//import likelion.univ.alarm.dto.AlarmContentsDto;
//import likelion.univ.annotation.UseCase;
//import likelion.univ.domain.user.entity.User;
//import likelion.univ.domain.user.service.UserDomainService;
//import likelion.univ.sms.SmsContent;
//import likelion.univ.sms.SmsSender;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@UseCase
//@RequiredArgsConstructor
//public class SmsAlarmUsecase implements AlarmUsecase {
//
//    private final SmsSender smsSender;
//    private final UserDomainService userService;
//
//    public void execute(AlarmContentsDto alarmContentsDto) {
////                 TODO
////                List<String> phones = alarmContentsDto.getEmails().stream()
////                .map(email -> userService.findByEmail(email))
////                .map(user -> user.getProfile().getPhone())
////                .collect(Collectors.toList());
//
//        List<String> phones = Arrays.asList("010-1234-1234");
//
//        SmsContent smsContent = SmsContent.builder()
//                .contents(alarmContentsDto.getContent())
//                .receivers(phones)
//                .build();
//
//        smsSender.send(smsContent);
//    }
//
//}
