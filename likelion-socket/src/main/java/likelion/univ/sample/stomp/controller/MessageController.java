package likelion.univ.sample.stomp.controller;

import likelion.univ.sample.stomp.model.ChatMessage;
import likelion.univ.sample.stomp.service.ChatService;
import likelion.univ.sample.stomp.service.YourService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;
    private final YourService yourService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
        }

        // Set the current time in the sentTime field
        message.setSentTime(LocalDateTime.now());

        // Format the sentTime to a human-readable string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedSentTime = message.getSentTime().format(formatter);

        // Add the formattedSentTime as a separate field in the message
        message.setFormattedSentTime(formattedSentTime);

        // 데이터 저장

        chatService.saveChatMessage(message);


        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);

    /*
    private final YourService yourService;
    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");

            yourService.someMethod();
        }

        // Set the current time in the sentTime field
        message.setSentTime(LocalDateTime.now());

        // Format the sentTime to a human-readable string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedSentTime = message.getSentTime().format(formatter);

        // Add the formattedSentTime as a separate field in the message
        message.setFormattedSentTime(formattedSentTime);

        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    */
    }
}