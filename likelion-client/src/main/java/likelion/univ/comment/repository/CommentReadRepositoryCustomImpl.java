package likelion.univ.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.comment.dto.CommentDetailResponseDto;
import likelion.univ.domain.comment.dto.QCommentDetailResponseDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.like.commentlike.entity.QCommentLike.commentLike;
import static likelion.univ.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class CommentReadRepositoryCustomImpl implements CommentReadRepositoryCustom {
        private final JPAQueryFactory queryFactory;
        private final AuthentiatedUserUtils userUtils;

        @Override
        public List<CommentDetailResponseDto> findAll(Long postId) {
                return queryFactory
                        .select(commentDetailResponseDto())
                        .from(comment)
                        .join(comment.author, user)
//                        .leftJoin(comment.commentLikes, commentLike)
                        .where(comment.post.id.eq(postId))
                        .orderBy(comment.createdDate.asc())
                        .fetch();
        }

        @NotNull
        private static QCommentDetailResponseDto commentDetailResponseDto() {
                return new QCommentDetailResponseDto(
                        comment.id,
                        comment.author.id,
                        comment.author.profile.name,
                        comment.parentComment.id,
                        comment.body,
                        comment.commentLikes.size(),
                        comment.isDeleted);
        }
}
