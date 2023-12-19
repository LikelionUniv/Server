package likelion.univ.email;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import likelion.univ.annotation.Processor;
import likelion.univ.email.exception.EmailSendFailed;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@Processor
@RequiredArgsConstructor
public class AzureEmailProcessor {

    private final AzureCommunicationProperties properties;

    public void send(EmailContent emailContent) {
        EmailClient emailClient = setUpEmailClient();
        EmailMessage emailMessage = createEmailMessage(emailContent);
        sendEmail(emailClient, emailMessage);
    }

    private EmailClient setUpEmailClient() {
        String endpoint = properties.getEndpoint();
        AzureKeyCredential azureKeyCredential = new AzureKeyCredential(properties.getCredential());
        return new EmailClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                .buildClient();
    }

    private EmailMessage createEmailMessage(EmailContent emailContent) {
        ContentsType type = ContentsType.of(emailContent.getContentsType());
        EmailMessage emailMessage = type.setContents(emailContent.getContents());
        emailMessage
                .setSenderAddress(properties.getSender())
                .setToRecipients(
                        emailContent.getReceivers().stream()
                                .map(EmailAddress::new)
                                .toList()
                )
                .setSubject(emailContent.getSubject());

        return emailMessage;
    }

    private void sendEmail(EmailClient emailClient, EmailMessage message) {
        try {
            SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);

            PollResponse<EmailSendResult> pollResponse = null;

            Duration timeElapsed = Duration.ofSeconds(0);
            Duration POLLER_WAIT_TIME = Duration.ofSeconds(10);

            while (pollResponse == null
                    || pollResponse.getStatus() == LongRunningOperationStatus.NOT_STARTED
                    || pollResponse.getStatus() == LongRunningOperationStatus.IN_PROGRESS) {
                pollResponse = poller.poll();

                Thread.sleep(POLLER_WAIT_TIME.toMillis());
                timeElapsed = timeElapsed.plus(POLLER_WAIT_TIME);

                if (timeElapsed.compareTo(POLLER_WAIT_TIME.multipliedBy(18)) >= 0) {
                    throw new EmailSendFailed();
                }
            }

            if (!poller.getFinalResult().getStatus().equals(EmailSendStatus.SUCCEEDED)) {
                throw new EmailSendFailed();
            }
        } catch (Exception exception) {
            throw new EmailSendFailed();
        }
    }
}
