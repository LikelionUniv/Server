package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.dto.enums.PostOrderCondition;
import org.springframework.data.domain.Pageable;

public record GetPostsBySearchTitleCommand(
        PostOrderCondition orderCondition,
        String searchTitle
) {
}
