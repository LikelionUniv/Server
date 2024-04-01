package likelion.univ.domain.comment.repository.impl;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.commentlike.entity.QCommentLike.commentLike;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.repository.CommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findChildCommentsByPostId(Long postId) {
        List<Long> parentCommentsIds = getParentCommentsIds(postId);

        return queryFactory
                .select(comment)
                .from(comment)
                .where(comment.parentComment.id.in(parentCommentsIds))
                .join(comment.author, user)
                .leftJoin(comment.commentLikes, commentLike)
                .distinct()
                .fetch();
    }

    @Override
    public Long countByPostIdAndIsDeletedEquals(Long postId, Boolean isDeleted) {
        Long commentCount = queryFactory
                .select(comment.id.count())
                .from(comment)
                .join(comment.post, post)
                .on(comment.post.id.eq(postId))
                .where(comment.isDeleted.eq(isDeleted))
                .fetchFirst();
        return commentCount;
    }

    @Override
    public List<Comment> findParentCommentsByPostId(Long postId) {
        List<Long> parentCommentsIds = getParentCommentsIds(postId);

        return queryFactory
//                        .select(parentCommentData())
                .select(comment)
                .from(comment)
                .where(comment.id.in(parentCommentsIds))
                .join(comment.author, user)
                .leftJoin(comment.commentLikes, commentLike)
                .orderBy(comment.createdDate.asc())
                .distinct()
                .fetch();
    }

    private List<Long> getParentCommentsIds(Long postId) {
        return queryFactory
                .select(comment.id)
                .from(comment)
                .where(comment.post.id.eq(postId))
                .where(comment.parentComment.isNull())
                .fetch();
    }
}

