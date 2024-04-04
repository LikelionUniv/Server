package likelion.univ.post.processor;

import java.util.Optional;
import likelion.univ.annotation.Processor;
import likelion.univ.domain.comment.adaptor.CommentAdaptor;
import likelion.univ.domain.like.postlike.adaptor.PostLikeAdaptor;
import likelion.univ.post.dao.PostCountInfoRedisDao;
import likelion.univ.post.entity.PostCountInfo;
import likelion.univ.post.service.PostCountInfoRedisService;
import lombok.RequiredArgsConstructor;

@Processor
@RequiredArgsConstructor
public class GetOrCreatePostCountInfoProcessor {

    private final PostCountInfoRedisDao postCountInfoRedisDao;
    private final PostCountInfoRedisService postCountInfoRedisService;
    private final CommentAdaptor commentAdaptor;
    private final PostLikeAdaptor postLikeAdaptor;

    public PostCountInfo execute(Long postId) {
        Optional<PostCountInfo> postCountInfo = postCountInfoRedisDao.findById(postId);
        if (postCountInfo.isEmpty()) {
            Long commentCount = commentAdaptor.countByPostIdAndIsDeletedEquals(postId, false);
            Long likeCount = postLikeAdaptor.countByPostId(postId);
            return postCountInfoRedisService.save(postId, commentCount, likeCount);
        } else {
            return postCountInfo.get();
        }
    }
}
