//package likelion.univ.pubsub;
//
//
//import likelion.univ.model.ChatMessage;
//import likelion.univ.repo.ChatMessageRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class RedisPublisher {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//    private final ChatMessageRepository messageRepository;
//    public void publish(ChannelTopic topic, ChatMessage message) {
//        redisTemplate.convertAndSend(topic.getTopic(), message);
//        messageRepository.save(message);
//    }
//}
