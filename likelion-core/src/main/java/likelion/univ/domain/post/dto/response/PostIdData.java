package likelion.univ.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
public record PostIdData(
        Long postId
) {
}
