package likelion.univ.email.sender.azure;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import likelion.univ.annotation.Processor;
import likelion.univ.email.exception.EmailSendFailed;
import likelion.univ.email.exception.InvalidAttachment;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AzureEmailSender implements EmailSender {

    private final AzureCommunicationProperties properties;

    @Override
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
        EmailMessage emailMessage = new EmailMessage();
        emailMessage
                .setSenderAddress(properties.getSender())
                .setToRecipients(
                        emailContent.getReceivers().stream()
                                .map(EmailAddress::new)
                                .toList()
                )
                .setSubject(emailContent.getSubject())
                .setBodyHtml(emailContent.getContents())
                .setAttachments(getAttachments(emailContent.getAttachments()));

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

    private List<EmailAttachment> getAttachments(List<MultipartFile> attachments) {
        return attachments.stream()
                .map(attachment -> new EmailAttachment(
                            attachment.getOriginalFilename(),
                            attachment.getContentType(),
                            getBinaryData(attachment)
                        )
                )
                .toList();
    }

    private BinaryData getBinaryData(MultipartFile attachment) {
        try {
            return BinaryData.fromStream(attachment.getInputStream());
        } catch (IOException e) {
            throw new InvalidAttachment();
        }
    }
}
