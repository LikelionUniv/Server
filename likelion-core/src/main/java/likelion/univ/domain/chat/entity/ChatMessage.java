package likelion.univ.domain.chat.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "message")
@Getter
@Table(name = "message")
public class ChatMessage {


    @Id
    private String id;

    protected MessageType type;

    private String roomId; // ChatRoom과 연관관계 설정

    private String sender; // user과 연관관계 설정

    private String message;

    private boolean likeStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }


    public ChatMessage(String id, MessageType type, String roomId, String sender, String message, boolean likeStatus) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.likeStatus = likeStatus;
    }

}
