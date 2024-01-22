package likelion.univ.domain.like.postlike.repository;

import java.util.List;

public interface PostLikeCustomRepository {
    List<Long> findPostIdsByUserIdAndPostIdsIn(Long userId, List<Long> postIds);
}
