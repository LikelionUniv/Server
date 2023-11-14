package likelion.univ.user.dto.response;

import likelion.univ.domain.post.entity.Post;
import likelion.univ.post.entity.PostCountInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Builder
public class UserPagePostsDto {
    private Long id;
    private String title;
    private String body;
    private String thumbnail;
    private Boolean isAuthor;
    private LocalDate createdDate;
    private Long likeCount;
    private Long commentCount;

    public static UserPagePostsDto of(Post post, Long likeCount, Long commentCount, Boolean isAuthor){
        return UserPagePostsDto.builder()
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

    public static UserPagePostsDto of(Post post, Long userId, PostCountInfo postCountInfo){
        if(post.getAuthor().getId().equals(userId))
            return UserPagePostsDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),true);
        else return UserPagePostsDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),false);
    }
}
