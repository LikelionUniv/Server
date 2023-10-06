package likelion.univ.post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import likelion.univ.domain.post.dto.PostDetailResponseDto;
import likelion.univ.domain.post.dto.QPostDetailResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;


public class PostReadRepositoryCustomImpl implements PostReadRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<PostDetailResponseDto> findPostsByAuthor(Long authorId, Pageable pageable) {

        return queryFactory
                .select(new QPostDetailResponseDto(
                        post.id,
                        post.author.id,
                        post.author.profile.name,
                        post.title,
                        post.body,
                        post.thumbnail,
                        post.mainCategory,
                        post.subCategory,
                        post.createdDate,
                        post.modifiedDate))
                .from(post)
                .innerJoin(post.author, user)/*.fetchJoin()*/
                .on(post.author.id.eq(user.id))
                .orderBy(post.createdDate.desc())
                .where(
                        authorIdEq(authorId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    private static BooleanExpression authorIdEq(Long authorId) {
        return post.author.id.eq(authorId);
    }
}
