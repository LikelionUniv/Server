package likelion.univ.email.sender.azure;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailAddress;
import com.azure.communication.email.models.EmailAttachment;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.communication.email.models.EmailSendStatus;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import java.io.IOException;
import java.util.List;
import likelion.univ.email.exception.EmailSendFailed;
import likelion.univ.email.exception.InvalidAttachment;
import likelion.univ.email.sender.EmailContent;
import likelion.univ.email.sender.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message, null);
        PollResponse<EmailSendResult> response = poller.waitForCompletion();

        if (!response.getValue().getStatus().equals(EmailSendStatus.SUCCEEDED)) {
            throw new EmailSendFailed();
        }
    }

    private List<EmailAttachment> getAttachments(List<MultipartFile> attachments) {
        if (attachments == null || attachments.size() == 0) {
            return null;
        }
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
