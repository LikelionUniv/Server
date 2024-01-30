package likelion.univ.alarm.dto;

import likelion.univ.common.processor.DateCustomFormatter;
import likelion.univ.domain.alarm.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAlarmsDto {

    private List<AlarmDto> Alarms;

    public static GetAlarmsDto of(List<Alarm> alarms) {
        return new GetAlarmsDto(
                alarms.stream()
                        .map(AlarmDto::from)
                        .toList()
        );
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class AlarmDto {

        private Long id;
        private Long ordinal;
        private String email;
        private String createdDate;

        public static AlarmDto from(Alarm alarm) {
            return new AlarmDto(
                    alarm.getId(),
                    alarm.getOrdinal(),
                    alarm.getEmail(),
                    DateCustomFormatter.format(alarm.getCreatedDate())
            );
        }
    }
}
