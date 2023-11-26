package likelion.univ.domain.post.dto.request;

import org.springframework.data.domain.Pageable;

public record GetPostByAuthorServiceDto(
        Long authorId,
        Pageable pageable
) {
}
