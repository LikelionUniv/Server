package likelion.univ.response;

public class ChatRoomResponse {
    private String roomId;
    private String name;

    public ChatRoomResponse(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }
}
