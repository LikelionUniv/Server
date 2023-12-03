package likelion.univ.domain.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreatePostServiceDto {
    private String title;
    private String body;

    private Long authorId;

    private String thumbnail;

    private String mainCategory;

    private String subCategory;

}
