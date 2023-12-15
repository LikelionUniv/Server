package likelion.univ.alarm.usecase;

import likelion.univ.alarm.dto.EmailAlarmDto;
import likelion.univ.alarm.emailsender.EmailSender;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.email.EmailContent;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class EmailAlarmUsecase {

    private final UserDomainService userService;
    private final EmailSender emailSender;

    public void execute(EmailAlarmDto emailAlarmDto) {

        List<Role> roles = emailAlarmDto.getRoles().stream()
                .map(Role::valueOf)
                .toList();

        List<String> receivers = userService.findByRoleIn(roles).stream()
                .map(user -> user.getAuthInfo().getEmail())
                .toList();

        EmailContent emailContent = EmailContent.builder()
                .subject(emailAlarmDto.getSubject())
                .contents(emailAlarmDto.getContents())
                .receivers(receivers)
                .build();

        emailSender.send(emailContent);
    }
}
