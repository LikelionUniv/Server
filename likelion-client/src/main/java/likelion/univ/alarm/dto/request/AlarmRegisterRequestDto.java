package likelion.univ.alarm.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import likelion.univ.domain.alarm.entity.Alarm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static likelion.univ.domain.alarm.entity.SendStatus.NOT_SENT;

@Getter
@NoArgsConstructor
public class AlarmRegisterRequestDto {
    @NotNull
    @Schema(description = "이메일", example = "tmfrk0426@gmail.com", required = true)
    private String email;

    public static Alarm toEntity(Long ordinal, AlarmRegisterRequestDto alarmRegisterRequestDto){
        return Alarm.builder()
                .ordinal(ordinal)
                .email(alarmRegisterRequestDto.getEmail())
                .sendStatus(NOT_SENT)
                .build();
    }
}
