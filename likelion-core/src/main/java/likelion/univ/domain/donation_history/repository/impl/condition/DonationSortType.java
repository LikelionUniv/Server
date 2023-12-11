package likelion.univ.domain.donation_history.repository.impl.condition;

import com.querydsl.core.types.OrderSpecifier;
import likelion.univ.exception.SortTypeNotMatchedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static likelion.univ.domain.donation_history.entity.QDonationHistory.donationHistory;


@Getter
@AllArgsConstructor
public enum DonationSortType {
    CREATED_DATE("created_date"),
    VIEW_COUNT("view_count");

    private String value;

    public static DonationSortType fromValue(String value) {
        for (DonationSortType type : DonationSortType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new SortTypeNotMatchedException();
    }
    public static OrderSpecifier toOrderSpecifier(String value){
        switch (DonationSortType.fromValue(value)){
            case VIEW_COUNT:
                return donationHistory.viewCount.desc();
            default:
                return donationHistory.createdDate.desc();
        }
    }
}
