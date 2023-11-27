package likelion.univ.chat.controller;

import likelion.univ.domain.chat.entity.ChatRoom;
import likelion.univ.chat.usecase.ChatRoomUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Tag(name = "chat-room", description = "채팅방")
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomUseCase chatRoomService;

    @GetMapping("/room")
    @Operation(summary = "채팅방 목록 페이지 가져오기")
    public String rooms(Model model) {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @Operation(summary = "채팅방 목록 가져오기")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomService.findAllRoom();
    }

    @PostMapping("/room")
    @Operation(summary = "새로운 채팅방 만들기")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomService.createChatRoom(name);
    }

    @GetMapping("/room/enter/{roomId}")
    @Operation(summary = "채팅방 입장하기")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @Operation(summary = "채팅방 정보 가져오기")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomService.findRoomById(roomId);
    }
}
