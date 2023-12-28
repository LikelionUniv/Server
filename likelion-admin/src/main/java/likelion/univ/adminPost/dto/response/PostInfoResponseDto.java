package likelion.univ.adminPost.dto.response;

import likelion.univ.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PostInfoResponseDto {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Integer postLikeCount;
    private Integer commentsCounts;
    private LocalDateTime createDate;


    public static PostInfoResponseDto of(Post post, Integer postLikeCount, Integer commentsCounts){
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


    public static PostInfoResponseDto of(Post post) {

        return PostInfoResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .author(post.getAuthor().getProfile().getName())
                .createDate(post.getCreatedDate())
                .build();
    }

}

