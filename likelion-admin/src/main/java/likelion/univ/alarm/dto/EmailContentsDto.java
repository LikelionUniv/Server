package likelion.univ.alarm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class EmailContentsDto {
    private String subject;
    private String sender;
    private String content;
    private List<String> emails;
}
