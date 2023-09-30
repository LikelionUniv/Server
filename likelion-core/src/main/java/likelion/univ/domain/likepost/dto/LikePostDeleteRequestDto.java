package likelion.univ.domain.likepost.dto;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LikePostDeleteRequestDto {
    private Post post;
    private User author;

}
