package likelion.univ.email;

import com.azure.communication.email.models.EmailMessage;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public enum ContentsType {

    HTML("html", (contents) -> {
        EmailMessage emailMessage = new EmailMessage();
        return emailMessage.setBodyHtml(contents);
    }),
    PLAIN_TEXT("plain-text", (contents) -> {
        EmailMessage emailMessage = new EmailMessage();
        return emailMessage.setBodyHtml(contents);
    });

    private final String name;
    private final Function<String, EmailMessage> setContents;

    public static ContentsType of(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElse(PLAIN_TEXT);
    }

    public EmailMessage setContents(String contents) {
        return setContents.apply(contents);
    }
}
