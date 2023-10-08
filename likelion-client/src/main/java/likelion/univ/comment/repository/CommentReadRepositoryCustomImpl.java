package likelion.univ.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.post.repository.PostReadRepositoryCustom;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentReadRepositoryCustomImpl implements CommentReadRepositoryCustom {
        private final JPAQueryFactory queryFactory;
        private final AuthentiatedUserUtils userUtils;


}
