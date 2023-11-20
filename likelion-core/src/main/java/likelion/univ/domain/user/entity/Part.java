package likelion.univ.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Part {
    PM("기획"),
    DESIGNER("디자인"),
<<<<<<< HEAD
    PM_DESIGNER("기획/디자인");
=======
    PM_DESIGNER("기획/디자인"),
    FRONTEND("프론트엔드"),
    BACKEND("백엔드");
>>>>>>> 51497509e432a26e57f31debfb42a2364d4d2484
    private String value;
}
