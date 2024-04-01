package likelion.univ.domain.post.repository.impl.condition;

import static likelion.univ.domain.post.entity.QPost.post;

import com.querydsl.core.types.OrderSpecifier;
import likelion.univ.exception.SortTypeNotMatchedException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostSortType {
    CREATED_DATE("created_date"),
    LIKE("like"),
    COMMENT("comment");

    private String value;

    public static PostSortType fromValue(String value) {
        for (PostSortType type : PostSortType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new SortTypeNotMatchedException();
    }

    public static OrderSpecifier toOrderSpecifier(String value) {
        switch (PostSortType.fromValue(value)) {
            case LIKE:
                return post.postLikes.size().desc();
            case COMMENT:
                return post.comments.size().desc();
            default:
                return post.createdDate.desc();
        }
    }
}
