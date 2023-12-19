package likelion.univ.domain.post.repository.impl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.post.repository.PostCustomRepository;
import likelion.univ.domain.post.repository.impl.condition.PostSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.postlike.entity.QPostLike.postLike;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findByCommentAuthorId(Long userId, Pageable pageable){
        List<Long> ids = getCoveringIndexByComment(comment.author.id.eq(userId)
                                                     .and(comment.isDeleted.isFalse()));
        return findByCoveringIndex(ids, pageable);
    }

    @Override
    public Page<Post> findByPostLikeAuthorId(Long userId, Pageable pageable, String sort, String search){
        List<Long> ids = getCoveringIndexByPostLike(postLike.author.id.eq(userId));
        return findByPostLikesWithSort(ids, pageable, PostSortType.toOrderSpecifier(sort), search);
    }

    private List<Long> getCoveringIndexByComment(Predicate predicate) {
        return queryFactory.select(comment.post.id).from(comment).where(predicate).fetch();
    }
    private List<Long> getCoveringIndexByPostLike(Predicate predicate) {
        return queryFactory.select(postLike.post.id).from(postLike).where(predicate).fetch();
    }

    private BooleanExpression searchCondition(String search) {
        return StringUtils.hasText(search) ? post.body.contains(search).or(post.title.contains(search)) : null;
    }

    private  Page<Post> findByCoveringIndex(List<Long> ids, Pageable pageable){
        List<Post> posts =
                queryFactory
                        .select(post)
                        .from(post)
                        .innerJoin(post.author, user).fetchJoin()
                        .where(post.id.in(ids))
                        .offset(pageable.getOffset())
                        .orderBy(post.createdDate.desc())
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(posts, pageable, ids.size());
    }

    private  Page<Post> findByPostLikesWithSort(List<Long> ids, Pageable pageable, OrderSpecifier sort, String search){
        List<Post> posts =
                queryFactory
                        .selectDistinct(post)
                        .from(post)
                        .innerJoin(post.author, user).fetchJoin()
                        .leftJoin(post.postLikes, postLike)
                        .leftJoin(post.comments, comment)
                        .where(post.id.in(ids)
                                .and(searchCondition(search)))
                        .offset(pageable.getOffset())
                        .orderBy(sort)
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(posts, pageable, ids.size());
    }
}
