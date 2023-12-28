package likelion.univ.domain.comment.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import likelion.univ.domain.comment.dto.response.QChildCommentData;
import likelion.univ.domain.comment.dto.response.QParentCommentData;
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
        public List<Comment> findChildCommentsByPostId(Long postId) {
                List<Long> parentCommentsIds = getParentCommentsIds(postId);

                return queryFactory
//                        .select(childCommentData())
                        .select(comment)
                        .from(comment)
                        .where(comment.parentComment.id.in(parentCommentsIds))
                        .join(comment.author, user)
                        .leftJoin(comment.commentLikes, commentLike)
                        .distinct()
                        .fetch();
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

//        private static QParentCommentData parentCommentData() {
//                return new QParentCommentData(
//                        comment.id,
//                        comment.author.id,
//                        comment.author.profile.name,
//                        comment.author.profile.profileImage,
//                        comment.commentLikes.size(),
//                        comment.body,
//                        comment.isDeleted,
//                        comment.createdDate
//                );
//        }
//
//        private static QChildCommentData childCommentData() {
//                return new QChildCommentData(
//                        comment.id,
//                        comment.parentComment.id,
//                        comment.author.id,
//                        comment.author.profile.name,
//                        comment.author.profile.profileImage,
//                        comment.commentLikes.size(),
//                        comment.body,
//                        comment.isDeleted,
//                        comment.createdDate
//                );
//        }

}

