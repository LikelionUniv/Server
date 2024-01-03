package likelion.univ.domain.post.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;

@Getter
@RequiredArgsConstructor
public enum MainCategory {
    HQ_BOARD("멋대 중앙"),
    FREE_BOARD("자유게시판"),
    OVERFLOW("멋사 오버플로우");
    private final String title;
    public static MainCategory findByTitle(String title) {
        if (title.equals(HQ_BOARD.title)) {
            return HQ_BOARD;
        } else if (title.equals(FREE_BOARD.title)) {
            return FREE_BOARD;
        } else if (title.equals(OVERFLOW.title)) {
            return OVERFLOW;
        }
        return null;
    }

    public static boolean isValid(String title) {
        if (MainCategory.findByTitle(title) == null) {
            return false;
        }
        return true;
    }
}
