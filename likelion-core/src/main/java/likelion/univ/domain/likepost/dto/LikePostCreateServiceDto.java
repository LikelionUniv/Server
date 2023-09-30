package likelion.univ.domain.likepost.dto;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LikePostCreateServiceDto {
    private User author;
    private Post post;


}
