package likelion.univ.domain.post.dto;

import likelion.univ.domain.post.entity.enums.MainCategory;
import likelion.univ.domain.post.entity.enums.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostDetailResponseDto {
    private Long id;

    private Long authorId;
    private String author;

    private String title;

    private String body;

    private String thumbnail;

    private MainCategory mainCategory;

    private SubCategory subCategory;
}
