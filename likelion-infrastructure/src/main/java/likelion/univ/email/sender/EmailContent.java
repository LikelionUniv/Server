package likelion.univ.email.sender;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailContent {

    private String subject;
    private String contentsType;
    private String contents;
    private List<String> receivers;

    @Builder
    public EmailContent(String subject, String contentsType, String contents, List<String> receivers) {
        this.subject = subject;
        this.contentsType = contentsType;
        this.contents = contents;
        this.receivers = receivers;
    }
}
