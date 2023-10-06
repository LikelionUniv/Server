package likelion.univ.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import likelion.univ.domain.post.dto.response.PostDetailResponseDto;
import likelion.univ.domain.post.dto.response.QPostDetailResponseDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static likelion.univ.domain.comment.entity.QComment.comment;
import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;


public class PostReadRepositoryCustomImpl implements PostReadRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<PostDetailResponseDto> findPostsByAuthor(Long userId, Pageable pageable) {

        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .innerJoin(post.author, user)/*.fetchJoin()*/
                .orderBy(post.createdDate.desc())
                .where(
                        postAuthorEq(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    @Override
    public List<PostDetailResponseDto> findPostsByCommentAuthor(Long userId, Pageable pageable) {
        List<Long> postIds = queryFactory
                .select(comment.post.id)
                .from(comment)
//                .innerJoin(comment.post, post)
                .where(
                        commentAuthorEq(userId)
                )
                .fetch();
        return queryFactory
                .select(postDetailResponseDto())
                .from(post)
                .innerJoin(post.author, user)
                .orderBy(post.createdDate.desc())
                .where(
                        post.id.in(postIds)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
    @NotNull
    private static QPostDetailResponseDto postDetailResponseDto() {
        return new QPostDetailResponseDto(
                post.id,
                post.author.id,
                post.author.profile.name,
                post.title,
                post.body,
                post.thumbnail,
                post.mainCategory,
                post.subCategory,
                post.createdDate,
                post.modifiedDate);
    }

    /* 내부 메서드 */
    private static BooleanExpression postAuthorEq(Long userId) {
        return post.author.id.eq(userId);
    }

    private static BooleanExpression commentAuthorEq(Long userId) {
        return comment.author.id.eq(userId);
    }


}
