package likelion.univ.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.model.ChatMessage;
import likelion.univ.repo.ChatMessageRepository;
import likelion.univ.service.ChatMessageService;
import likelion.univ.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RestController
@Tag(name = "chat", description = "메시지")
@RequestMapping("/msg")
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomService.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomService.getTopic(message.getRoomId()), message);
    }
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @GetMapping("/msg/all")
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }
//    @GetMapping("/msg")
//    public ChatMessageResponse getInfo(){
//        Long chatId=1L;
//        ChatMessage chatMessage =chatMessageRepository.findById(chatId).orElse(new ChatMessage());
//        return new ChatMessageResponse(chatMessage.getId(),chatMessage.getType(),chatMessage.getRoomId(),chatMessage.getSender(),chatMessage.getMessage());

}
//    @ResponseBody
//    public List<Message> msg() {return chatMessageService.findAllMsg();    }


