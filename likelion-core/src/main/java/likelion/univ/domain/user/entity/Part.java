package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Part {
    PM("기획"),
    DESIGNER("디자인"),
    PM_DESIGNER("기획/디자인"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");

    private String value;

}
