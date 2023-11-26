package likelion.univ.domain.post.dto.request;

import org.springframework.data.domain.Pageable;

public record GetUserPostsServiceDto(
        Long userId,
        Pageable pageable
) {
}
