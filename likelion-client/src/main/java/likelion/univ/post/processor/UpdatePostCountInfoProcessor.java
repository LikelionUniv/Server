package likelion.univ.post.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Processor
@RequiredArgsConstructor
public class UpdatePostCountInfoProcessor {
    private final PostCountInfoRedisService postCountInfoRedisService;

    public void execute(Long postId, Long commentCount, Long likeCount) {
        postCountInfoRedisService.save(postId, commentCount, likeCount);
    }
}
