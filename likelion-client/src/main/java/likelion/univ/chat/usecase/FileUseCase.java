package likelion.univ.chat.usecase;


import likelion.univ.domain.chat.entity.FileUrl;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUseCase {
    private static final String FILES = "FILE";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, FileUrl> opsHashFile;

    public FileUseCase(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.opsHashFile = redisTemplate.opsForHash();
    }

    public List<FileUrl> findAllFile() {
        return opsHashFile.values(FILES);
    }
    public void saveFileUrl(String fileUrl, String roomId) {
        FileUrl fileUrlObj = new FileUrl();
        fileUrlObj.setUrl(fileUrl);
        fileUrlObj.setRoomId(roomId);
        opsHashFile.put(FILES, fileUrl, fileUrlObj);
    }
}
