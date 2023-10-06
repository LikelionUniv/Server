package likelion.univ.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
public class PostFindRequestDto {
    @Positive(message = "페이지 넘버는 양수여야 합니다.")
    private Integer page;
    @Positive(message = "페이지 크기는 양수여야 합니다.")
    private Integer size;
}
