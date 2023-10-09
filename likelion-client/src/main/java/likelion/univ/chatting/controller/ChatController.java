package likelion.univ.chatting.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.chatting.entity.ChatMessage;
import likelion.univ.chatting.repository.ChatMessageRepository;
import likelion.univ.chatting.usecase.ChatMessageUseCase;
import likelion.univ.chatting.usecase.ChatRoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RestController
@Tag(name = "chat", description = "메시지")
@RequestMapping("/msg")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    private final RedisPublisher redisPublisher;
    private final ChatMessageUseCase chatMessageUseCase;
    private final ChatRoomUseCase chatRoomUseCase;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomUseCase.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomUseCase.getTopic(message.getRoomId()), message);
    }

    @GetMapping("/msg/all")
    @ResponseBody
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }
}


