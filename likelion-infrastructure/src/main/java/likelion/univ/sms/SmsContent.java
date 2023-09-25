package likelion.univ.sms;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SmsContent {

    private String contents;

    private List<String> receivers;

    @Builder
    public SmsContent(String contents, List<String> receivers) {
        this.contents = contents;
        this.receivers = formatPhones(receivers);
    }

    private List<String> formatPhones(List<String> phones) {
        return phones.stream()
                .map(phone -> phone.replaceAll("-", ""))
                .collect(Collectors.toList());
    }
}
