package likelion.univ.domain.like.postlike.dto;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostLikeCreateServiceDto {
    private Long authorId;
    private Long postId;


}
