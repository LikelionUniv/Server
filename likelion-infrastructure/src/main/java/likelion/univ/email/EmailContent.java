package likelion.univ.email;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailContent {

    private String subject;
    private String sender;
    private String contents;
    private List<String> receivers;

    @Builder
    public EmailContent(String subject, String sender, String contents, List<String> receivers) {
        this.subject = subject;
        this.sender = sender;
        this.contents = contents;
        this.receivers = receivers;
    }
}
