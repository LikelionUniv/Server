package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountStatus {
    ACTIVE("ACTIVE"),
    Deleted("DELETED");

    private String value;
}
