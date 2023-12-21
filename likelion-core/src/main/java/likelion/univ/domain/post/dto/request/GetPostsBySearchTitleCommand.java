package likelion.univ.domain.post.dto.request;

import org.springframework.data.domain.Pageable;

public record GetPostsBySearchTitleCommand(
        String searchTitle,
        Pageable pageable
) {
}
