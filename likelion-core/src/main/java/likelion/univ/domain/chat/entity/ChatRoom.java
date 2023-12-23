package likelion.univ.domain.chat.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Document(collection = "room")
@Getter
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    @Id
    private String id;
    private String name;
    private String roomId;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoomId() {
        return roomId;
    }

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
    public ChatRoom(){}

    public ChatRoom(String id, String name, String roomId) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
    }



}
