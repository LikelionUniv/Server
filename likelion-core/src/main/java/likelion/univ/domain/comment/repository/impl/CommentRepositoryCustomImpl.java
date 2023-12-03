package likelion.univ.domain.comment.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.comment.dto.ParentCommentDetailResponseDto;
import likelion.univ.domain.comment.dto.QParentCommentDetailResponseDto;
import likelion.univ.domain.comment.entity.Comment;
import likelion.univ.domain.comment.repository.CommentRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.commentlike.entity.QCommentLike.commentLike;
import static likelion.univ.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
        private final JPAQueryFactory queryFactory;

        @Override
        public List<Comment> findCommentsByPostId(Long postId) {
                return queryFactory
                        .selectFrom(comment)
                        .where(comment.post.id.eq(postId))
                        .where(comment.parentComment.isNull())
                        .orderBy(comment.createdDate.asc())
                        .join(comment.author, user)
                        .leftJoin(comment.commentLikes, commentLike)
                        .leftJoin(comment.childComments, comment)
                        .fetch();
        }

        private static QParentCommentDetailResponseDto parentCommentDetailResponseDto() {
                return new QParentCommentDetailResponseDto(
                        comment.id,
                        comment.author.id,
                        comment.author.profile.name,
                        comment.parentComment.id,
                        comment.body,
                        comment.commentLikes.size(),
                        comment.isDeleted);
        }
}
