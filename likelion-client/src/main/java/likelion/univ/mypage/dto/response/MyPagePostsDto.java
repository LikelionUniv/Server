package likelion.univ.mypage.dto.response;

import likelion.univ.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class MyPagePostsDto {
    private Long id;
    private String title;
    private String body;
    private String thumbnail;
    private Boolean isAuthor;
    private LocalDate createdDate;
    private Long likeCount;
    private Long commentCount;

    public static MyPagePostsDto of(Post post, Long likeCount, Long commentCount){
        return MyPagePostsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .thumbnail(post.getThumbnail())
                .isAuthor(true)
                .createdDate(post.getCreatedDate().toLocalDate())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .build();
    }
    public static MyPagePostsDto withAuthorOf(Post post, Long likeCount, Long commentCount, Boolean isAuthor){
        return MyPagePostsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .thumbnail(post.getThumbnail())
                .isAuthor(isAuthor)
                .createdDate(post.getCreatedDate().toLocalDate())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .build();
    }
}
