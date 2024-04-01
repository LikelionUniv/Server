package likelion.univ.alarm.dto.request;

import static likelion.univ.domain.alarm.entity.SendStatus.NOT_SENT;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.alarm.entity.Alarm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
public class AlarmRegisterRequestDto {
    @NotNull
    @Schema(description = "이메일", example = "tmfrk0426@gmail.com", required = true)
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    public static Alarm toEntity(Long ordinal, AlarmRegisterRequestDto alarmRegisterRequestDto) {
        return Alarm.builder()
                .ordinal(ordinal)
                .email(alarmRegisterRequestDto.getEmail())
                .sendStatus(NOT_SENT)
                .build();
    }
}
