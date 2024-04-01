package likelion.univ.domain.like.postlike.repository.impl;

import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import likelion.univ.domain.like.postlike.repository.PostLikeCustomRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostLikeCustomRepositoryImpl implements PostLikeCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findPostIdsByUserIdAndPostIdsIn(Long userId, List<Long> postIds) {
        List<Long> ids = queryFactory.select(postLike.post.id)
                .from(postLike)
                .where(postLike.user.id.eq(userId), postLike.post.id.in(postIds))
                .fetch();
        return ids;
    }
}
