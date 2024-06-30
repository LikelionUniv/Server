package likelion.univ.domain.hackathon.repository.impl;

import static likelion.univ.domain.hackathon.entity.QHackathon.hackathon;
import static likelion.univ.domain.hackathon.entity.QHackathonForm.hackathonForm;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import likelion.univ.domain.hackathon.entity.HackathonForm;
import likelion.univ.domain.hackathon.repository.HackathonFormCustomRepository;
import likelion.univ.domain.hackathon.repository.condition.HackathonFormSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class HackathonFormCustomRepositoryImpl implements HackathonFormCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<HackathonForm> search(HackathonFormSearchCondition condition, Pageable pageable) {
        List<Long> ids = getCoveringIndex(keywordContains(condition.keyword()));

        List<HackathonForm> result = queryFactory.select(hackathonForm)
                .from(hackathonForm)
                .leftJoin(hackathonForm.university)
                .fetchJoin()
                .where(hackathonForm.id.in(ids))
                .orderBy(hackathonForm.createdDate.desc())
                .offset(condition.isExcelData() ? 0 : pageable.getOffset())
                .limit(
                        condition.isExcelData()
                                ? ids.size() + 1
                                : pageable.getPageSize())
                .fetch();

        return condition.isExcelData()
                ? PageableExecutionUtils.getPage(
                result, PageRequest.of(0, ids.size() + 1), ids::size)
                : PageableExecutionUtils.getPage(result, pageable, ids::size);
    }

    @Override
    public List<HackathonForm> findByUserId(Long userId) {
        return queryFactory.selectFrom(hackathonForm)
                .leftJoin(hackathonForm.hackathon, hackathon).fetchJoin()
                .where(hackathonForm.user.id.eq(userId))
                .orderBy(hackathonForm.createdDate.desc())
                .fetch();
    }

    /**
     * Page 기반 페이지네이션을 위한 커버링인덱스 구하는 함수
     *
     * @param predicate 탐색 조건
     * @return 커버링 인덱스
     */
    private List<Long> getCoveringIndex(Predicate predicate) {
        return queryFactory.select(hackathonForm.id).from(hackathonForm).where(predicate).fetch();
    }

    /**
     * 검색어
     */
    private BooleanExpression keywordContains(String keyword) {
        return keyword != null
                ? hackathonForm.name.contains(keyword)
                .or(hackathonForm.university.name.contains(keyword))
                : null;
    }
}
