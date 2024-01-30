package likelion.univ.domain.user.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import likelion.univ.common.processor.ConvertSliceProcessor;
import likelion.univ.domain.user.entity.AccountStatus;
import likelion.univ.domain.user.entity.Part;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.repository.UserCustomRepository;
import likelion.univ.domain.user.repository.searchcondition.UserSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.util.List;

import static likelion.univ.domain.follow.entity.QFollow.follow;
import static likelion.univ.domain.university.entity.QUniversity.university;
import static likelion.univ.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final ConvertSliceProcessor convertSliceProcessor;

    @Override
    public List<User> findDynamicUsers(UserSearchCondition condition) {
        return queryFactory
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

    private BooleanExpression eqRole(Role role) {
        return role != null ?
                switch (role){
                    case GUEST -> user.authInfo.role.eq(Role.GUEST);
                    case USER -> user.authInfo.role.eq(Role.USER);
                    case MANAGER -> user.authInfo.role.eq(Role.MANAGER);
                    case UNIVERSITY_ADMIN -> user.authInfo.role.eq(Role.UNIVERSITY_ADMIN);
                    case SUPER_ADMIN -> user.authInfo.role.eq(Role.SUPER_ADMIN);
                }
                : null;
    }


    @Override
    public Page<User> findByUnivNameAndRole(Role role, String univName, Pageable pageable){
        List<User> users =
                queryFactory
                        .select(user)
                        .from(user)
                        .innerJoin(user.universityInfo.university, university).fetchJoin()
                        .where(eqUniversity(univName),
                                (eqRole(role)),
                                (user.authInfo.accountStatus.eq(AccountStatus.ACTIVE)))
                        .orderBy(user.createdDate.desc())
                        .fetch();

        return new PageImpl<>(users, pageable, users.size());

    }


    @Override
    public Slice<User> findFollowingUsersByFollowerID(Long followerId, Pageable pageable){
        List<User> users =
                queryFactory
                        .select(follow.following)
                        .from(follow)
                        .innerJoin(follow.following, user)
                        .where(follow.follower.id.eq(followerId))
                        .offset(pageable.getOffset())
                        .orderBy(user.createdDate.desc())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();

        return convertSliceProcessor.execute(users, pageable);
    }
    @Override
    public Slice<User> findFollowerUsersByFollowingID(Long followingId, Pageable pageable){
        List<User> users =
                queryFactory
                        .select(user)
                        .from(follow)
                        .innerJoin(follow.follower, user)
                        .where(follow.following.id.eq(followingId))
                        .offset(pageable.getOffset())
                        .orderBy(user.createdDate.desc())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();

        return convertSliceProcessor.execute(users, pageable);
    }

    @Override
    public List<User> findMyFollowingUsersByFollowingIdIn(Long followerId, List<Long> followingIdList){
        List<User> users =
                queryFactory
                        .select(user)
                        .from(follow)
                        .innerJoin(follow.following, user)
                        .where(follow.follower.id.eq(followerId)
                                .and(follow.following.id.in(followingIdList)))
                        .fetch();
        return users;
    }
    @Override
    public Slice<User> searchByName(String name, Pageable pageable){
        NumberExpression<Integer> partOrder = new CaseBuilder()
                .when(user.profile.part.eq(Part.PM)).then(1)
                .when(user.profile.part.eq(Part.DESIGNER)).then(2)
                .when(user.profile.part.eq(Part.PM_DESIGNER)).then(3)
                .when(user.profile.part.eq(Part.FRONTEND)).then(4)
                .when(user.profile.part.eq(Part.BACKEND)).then(5)
                .otherwise(6);

        List<User> users =
                queryFactory
                        .select(user)
                        .from(user)
                        .innerJoin(user.universityInfo.university, university).fetchJoin()
                        .where(containsName(name))
                        .offset(pageable.getOffset())
                        .orderBy(partOrder.asc(), user.profile.name.asc())
                        .limit(pageable.getPageSize() + 1)
                        .fetch();

        return convertSliceProcessor.execute(users, pageable);
    }
}