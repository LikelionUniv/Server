package likelion.univ.post.service;

import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCountInfoRedisService {
    private final PostCountInfoRedisDao postCountInfoRedisDao;

    public PostCountInfo save(Long postId, Long commentCount, Long likeCount){
        PostCountInfo postCountInfo = PostCountInfo.builder()
                .commentCount(commentCount)
                .likeCount(likeCount)
                .build();
        postCountInfoRedisDao.save(postId, postCountInfo);
        return postCountInfo;
    }
}
