package likelion.univ.domain.like.postlike.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.like.postlike.repository.PostLikeCustomRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;

@RequiredArgsConstructor
public class PostLikeCustomRepositoryImpl implements PostLikeCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findPostIdsByUserIdAndPostIdsIn(Long userId, List<Long> postIds){
        List<Long> ids = queryFactory.select(postLike.post.id)
                .from(postLike)
                .where(postLike.user.id.eq(userId), postLike.post.id.in(postIds))
                .fetch();
        return ids;
    }
}
