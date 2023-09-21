package likelion.univ.sms;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SmsSender {

    private final String API_KEY;
    private final String API_SECRET;
    private final String SENDING_NUMBER;

    public SmsSender(
            @Value("${alarm.sms.api-key}") String apiKey,
            @Value("${alarm.sms.api-secret}") String apiSecret,
            @Value("${alarm.sms.sending-number}") String sendingNumber) {
        this.API_KEY = apiKey;
        this.API_SECRET = apiSecret;
        this.SENDING_NUMBER = sendingNumber;
    }

    public void send(SmsContent smsContent) {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET, "https://api.solapi.com");

        List<Message> messages = new ArrayList<>();
        for (String receiver : smsContent.getReceivers()) {
            Message message = new Message();
            message.setFrom(SENDING_NUMBER);
            message.setTo(receiver);
            message.setText(smsContent.getContents());
            messages.add(message);
        }

        try {
            messageService.send(messages);
        } catch (NurigoMessageNotReceivedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO
     * https://msg.nurigo.co.kr
     * 아래 콘솔에서 API KEY, SECRET 발급, 발신번호 추가 절차 필요
     * 이후 application.yml 에 설정
     */
}
