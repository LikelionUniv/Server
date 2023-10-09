package likelion.univ.chatting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.chatting.entity.ChatRoom;
import likelion.univ.chatting.usecase.ChatRoomUseCase;
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

    private final ChatRoomUseCase chatRoomUseCase;

    @GetMapping("/room")
    @Operation(summary = "방 조회")
    public String rooms(Model model) {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomUseCase.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        return chatRoomUseCase.createChatRoom(name);
    }
    //public ChatRoom createRoom

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomUseCase.findRoomById(roomId);
    }
}
