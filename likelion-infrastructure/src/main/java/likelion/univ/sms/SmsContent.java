package likelion.univ.sms;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsContent {

    private String contents;

    private List<String> receivers;

    @Builder
    public SmsContent(String contents, List<String> receivers) {
        this.contents = contents;
        this.receivers = receivers;
    }
}
