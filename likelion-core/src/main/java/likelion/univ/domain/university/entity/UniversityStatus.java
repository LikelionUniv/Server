package likelion.univ.domain.university.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UniversityStatus {

    ACTIVE("ACTIVE"),
    DEACTIVE("DEACTIVE");

    private String value;
}
