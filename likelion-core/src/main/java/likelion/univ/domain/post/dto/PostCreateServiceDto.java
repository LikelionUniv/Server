package likelion.univ.domain.post.dto;

import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostCreateServiceDto {
    private String title;
    private String body;

    private Long authorId;

    private String thumbnail;

    private String mainCategory;

    private String subCategory;

}
