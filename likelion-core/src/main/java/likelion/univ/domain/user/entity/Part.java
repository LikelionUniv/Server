package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Part {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드"),
    PM("기획"),
    DESIGNER("디자인"),
    PM_DESIGNER("기획/디자인");

    private String value;


}
