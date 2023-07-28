package likelion.univ.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MainCategory {
    BOARD("MAINCATEGORY_MAIN", "멋대중앙"),
    HOMEWORK("MAINCATEGORY_FREEBOARD", "자유게시판"),
    PROJECT("MAINCATEGORY_OVERFLOW", "멋사오버플로우");


    private final String key;
    private final String title;
}
