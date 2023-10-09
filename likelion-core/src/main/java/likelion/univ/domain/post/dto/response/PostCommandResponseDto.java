package likelion.univ.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostCommandResponseDto {
    private Long postId;
}
