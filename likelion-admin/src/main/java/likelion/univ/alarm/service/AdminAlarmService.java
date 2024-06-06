package likelion.univ.alarm.service;

import java.util.List;
import likelion.univ.alarm.dto.GetAlarmsDto;
import likelion.univ.alarm.dto.SendEmailDto;
import likelion.univ.domain.alarm.entity.Alarm;
import likelion.univ.domain.alarm.service.AlarmService;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AdminAlarmService {

    private final AlarmService alarmService;
    private final EmailSender emailSender;

    @Transactional
    public void deleteAlarm(Long id) {
        alarmService.deleteById(id);
    }

    @Transactional(readOnly = true)
    public GetAlarmsDto getAlarms(Long ordinal) {
        List<Alarm> alarms = alarmService.findAllByOrdinal(ordinal);
        return GetAlarmsDto.of(alarms);
    }

    public void sendEmail(SendEmailDto sendEmailDto, List<MultipartFile> attachments) {
        EmailContent emailContent = EmailContent.builder()
                .subject(sendEmailDto.getSubject())
                .contents(sendEmailDto.getContents())
                .receivers(sendEmailDto.getReceivers())
                .attachments(attachments)
                .build();
        emailSender.send(emailContent);
    }
}
