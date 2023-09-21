package likelion.univ.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static likelion.univ.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(
                        containsName(condition.getName()),
                        eqUniversity(condition.getUniversity()),
                        eqPart(condition.getPart())
                )
                .fetch();
    }

    private BooleanExpression containsName(String searchName) {
        return StringUtils.hasText(searchName) ? user.profile.name.contains(searchName) : null;
    }

    private BooleanExpression eqUniversity(String searchUniversity) {
        return searchUniversity != null ? user.universityInfo.university.name.eq(searchUniversity) : null;
    }

    private BooleanExpression eqPart(String searchPart) {
        return searchPart != null ? user.profile.part.eq(Part.valueOf(searchPart)) : null;
    }


}
