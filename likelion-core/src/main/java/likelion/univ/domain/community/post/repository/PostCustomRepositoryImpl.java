package likelion.univ.domain.community.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import likelion.univ.domain.community.post.entity.Post;

import static likelion.univ.domain.entity.QPost.post;
import static likelion.univ.domain.entity.QUser.user;
import java.util.List;

@Repository
public class PostCustomRepositoryImpl implements PostCustomRepository{
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> pagingWithCoveringIndex(Integer page, Integer limit) {

        return null;
//        List<Long> ids = jpaQueryFactory
//                .select(post.id)
//                .from(post)
//                .limit(limit)
//                .offset(page * limit)
//                .fetch();
//
//        return jpaQueryFactory
//                .selectFrom(post)
//                .innerJoin(post.author, user)
//                .fetchJoin()
//                .where(post.id.in(ids))
//                .fetch();
    }
}
