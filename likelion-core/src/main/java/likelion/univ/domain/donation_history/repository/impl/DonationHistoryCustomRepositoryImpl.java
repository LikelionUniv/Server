package likelion.univ.domain.donation_history.repository.impl;

import static likelion.univ.domain.donation_history.entity.QDonationHistory.donationHistory;
import static likelion.univ.domain.user.entity.QUser.user;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import likelion.univ.domain.donation_history.entity.DonationHistory;
import likelion.univ.domain.donation_history.repository.DonationHistoryCustomRepository;
import likelion.univ.domain.donation_history.repository.impl.condition.DonationSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class DonationHistoryCustomRepositoryImpl implements DonationHistoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<DonationHistory> searchDonationHistoryWithSort(Pageable pageable, String sort, String search) {
        List<Long> ids = getCoveringIndex(searchCondition(search));
        return findDonationHistoryWithSort(ids, pageable, DonationSortType.toOrderSpecifier(sort));
    }

    private List<Long> getCoveringIndex(Predicate predicate) {
        return queryFactory.select(donationHistory.id).from(donationHistory).where(predicate).fetch();
    }

    private BooleanExpression searchCondition(String search) {
        return StringUtils.hasText(search) ? donationHistory.body.contains(search)
                .or(donationHistory.title.contains(search)) : null;
    }

    private Page<DonationHistory> findDonationHistoryWithSort(List<Long> ids, Pageable pageable, OrderSpecifier sort) {
        List<DonationHistory> donationHistories =
                queryFactory
                        .select(donationHistory)
                        .from(donationHistory)
                        .innerJoin(donationHistory.author, user).fetchJoin()
                        .where(donationHistory.id.in(ids))
                        .offset(pageable.getOffset())
                        .orderBy(sort)
                        .limit(pageable.getPageSize())
                        .fetch();

        return new PageImpl<>(donationHistories, pageable, ids.size());
    }
}
