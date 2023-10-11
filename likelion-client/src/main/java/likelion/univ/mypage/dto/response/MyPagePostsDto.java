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
    private Boolean isAuthor = true;
    private LocalDate createdDate;

    public static MyPagePostsDto of(Post post){
        return MyPagePostsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .thumbnail(post.getThumbnail())
                .createdDate(post.getCreatedDate().toLocalDate())
                .build();
    }
}
