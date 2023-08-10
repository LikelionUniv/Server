package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountStatus {
    NOT_MEMBER("NOT_MEMBER"),
    MEMEBER("MEMBER"),
    Deleted("DELETED");

    private String value;
}
