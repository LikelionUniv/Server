package likelion.univ.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.post.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static likelion.univ.domain.post.entity.QPost.post;
import static likelion.univ.domain.user.entity.QUser.user;

@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> pagingWithCoveringIndex(Integer page, Integer limit) {

        List<Long> ids = jpaQueryFactory
                .select(post.id)
                .from(post)
                .limit(limit)
                .offset(page * limit)
                .fetch();

        return jpaQueryFactory
                .selectFrom(post)
                .innerJoin(post.author, user)
                .fetchJoin()
                .where(post.id.in(ids))
                .fetch();
    }
}
