package likelion.univ.domain.comment.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.comment.dto.*;
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
        public List<CommentDetailResponseDto> findCommentsByPostId(Long postId, Long loginUserId) {
                List<Long> parentIds = queryFactory
                        .select(comment.id)
                        .from(comment)
                        .where(comment.post.id.eq(postId))
                        .where(comment.parentComment.isNull())
                        .fetch();

                List<ChildCommentDetailResponseDto> childComments = queryFactory
                        .select(childCommentDetailResponseDto(loginUserId))
                        .from(comment)
                        .where(comment.parentComment.id.in(parentIds))
                        .join(comment.author, user)
                        .leftJoin(comment.commentLikes, commentLike)
                        .fetch();

                List<ParentCommentDetailResponseDto> parentComments = queryFactory
                        .select(parentCommentDetailResponseDto(loginUserId))
                        .from(comment)
                        .where(comment.id.in(parentIds))
                        .join(comment.author, user)
                        .leftJoin(comment.commentLikes, commentLike)
                        .orderBy(comment.createdDate.asc())
                        .fetch();
                List<CommentDetailResponseDto> response = parentComments.stream().map(i -> CommentDetailResponseDto.of(i, childComments)).toList();
                return response;
        }

        private static QParentCommentDetailResponseDto parentCommentDetailResponseDto(Long loginUserId) {
                return new QParentCommentDetailResponseDto(
                        comment.id,
                        comment.author.id,
                        comment.author.profile.name,
                        comment.author.profile.profileImage,
                        comment.author.id.eq(loginUserId),
                        comment.commentLikes.size(),
                        comment.body,
                        comment.isDeleted,
                        comment.createdDate);
        }

        private static QChildCommentDetailResponseDto childCommentDetailResponseDto(Long loginUserId) {
                return new QChildCommentDetailResponseDto(
                        comment.id,
                        comment.parentComment.id,
                        comment.author.id,
                        comment.author.profile.name,
                        comment.author.profile.profileImage,
                        comment.author.id.eq(loginUserId),
                        comment.commentLikes.size(),
                        comment.body,
                        comment.isDeleted,
                        comment.createdDate
                );
        }
}

