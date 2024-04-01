package likelion.univ.email.sender;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailContent {
    private String subject;
    private String contentsType;
    private String contents;
    private List<String> receivers;
    private List<MultipartFile> attachments;

    @Builder
    public EmailContent(String subject,
                        String contentsType,
                        String contents,
                        List<String> receivers,
                        List<MultipartFile> attachments) {
        this.subject = subject;
        this.contentsType = contentsType;
        this.contents = contents;
        this.receivers = receivers;
        this.attachments = attachments;
    }
}
