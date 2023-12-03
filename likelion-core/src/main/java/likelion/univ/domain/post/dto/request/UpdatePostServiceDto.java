package likelion.univ.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdatePostServiceDto {
    private Long loginUserId;
    private Long postId;
    private String title;

    private String body;

    private String thumbnail;
}
