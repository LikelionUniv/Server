//package likelion.univ.model;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.UUID;
//
//@Entity
//@Getter
//@Setter
//public class ChatRoom implements Serializable {
//
//    private static final long serialVersionUID = 6494678977089006639L;
//
//    @Id
//    private String roomId;
//    private String name;
//
//    public static ChatRoom create(String name) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.roomId = UUID.randomUUID().toString();
//        chatRoom.name = name;
//        return chatRoom;
//    }
//}
