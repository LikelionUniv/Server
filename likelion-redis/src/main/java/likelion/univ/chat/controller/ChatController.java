package likelion.univ.chat.controller;

import likelion.univ.domain.chat.entity.ChatMessage;
import likelion.univ.chat.pubsub.RedisPublisher;
import likelion.univ.domain.chat.repository.ChatMessageRepository;
import likelion.univ.chat.usecase.ChatRoomUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private final ChatRoomUseCase chatRoomService;


    @MessageMapping("/chat/message")
    @Operation(summary = "채팅 메시지 전송")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomService.enterChatRoom(message.getRoomId());
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisPublisher.publish(chatRoomService.getTopic(message.getRoomId()), message);
    }

    @GetMapping("/msg/all")
    @Operation(summary = "전체 채팅 내용 조회")
    @ResponseBody
    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }


    @PostMapping("/like/add")
    @Operation(summary = "좋아요 추가")
    public ResponseEntity<String> addLike(@RequestBody ChatMessage message) {
        message.setLikeStatus(true);
        chatMessageRepository.save(message);
        return ResponseEntity.ok("Like operation performed successfully");
    }

    @DeleteMapping("/like/delete")
    @Operation(summary = "좋아요 제거")
    public ResponseEntity<String> deleteLike(@RequestBody ChatMessage message) {
        message.setLikeStatus(false);
        chatMessageRepository.save(message);
        return ResponseEntity.ok("Like operation performed successfully");
    }

}
