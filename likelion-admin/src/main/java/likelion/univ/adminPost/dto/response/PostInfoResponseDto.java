package likelion.univ.adminPost.dto.response;

import likelion.univ.domain.post.adaptor.PostAdaptor;
import likelion.univ.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostInfoResponseDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Long postLikeCount;
    private Long commentsCounts;
    private LocalDateTime createDate;

    private PostAdaptor postAdaptor;


    public static PostInfoResponseDto of(Post post, Long postLikeCount, Long commentsCounts){
        return new PostInfoResponseDtoBuilder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .author(post.getAuthor().getProfile().getName())
                .postLikeCount(postLikeCount)
                .commentsCounts(commentsCounts)
                .createDate(post.getCreatedDate())
                .build();

    }
}

