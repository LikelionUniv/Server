package likelion.univ.like.postlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostLikeRequestDto {

    private Long postId;
}
