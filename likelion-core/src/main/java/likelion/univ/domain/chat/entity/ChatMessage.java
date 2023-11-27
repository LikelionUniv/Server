package com.websocket.chat.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "message")
public class ChatMessage {

    // 메시지 타입 : 입장 0, 채팅 1
    public enum MessageType {
        ENTER, TALK
    }
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private MessageType type;

    private String roomId;

    @Column(name = "sender")
    private String sender;

    @Column(name = "message")
    private String message;

    @Column(name = "like_status")
    private boolean likeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

}
