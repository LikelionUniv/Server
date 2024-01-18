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
    private Boolean isLiked;
    private LocalDate createdDate;
    private Long likeCount;
    private Long commentCount;

    public static UserPagePostsDto of(Post post, Long likeCount, Long commentCount, Boolean isAuthor, Boolean isLiked){
        String body = post.getBody().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        if(body.length() > 100)
            body = body.substring(0,97) + "...";
        return UserPagePostsDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(body)
                .thumbnail(post.getThumbnail())
                .isAuthor(isAuthor)
                .isLiked(isLiked)
                .createdDate(post.getCreatedDate().toLocalDate())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .build();
    }

    public static UserPagePostsDto of(Post post, Long userId, PostCountInfo postCountInfo, Boolean isLiked){
        if(post.getAuthor().getId().equals(userId))
            return UserPagePostsDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),true, isLiked);
        else return UserPagePostsDto.of(post, postCountInfo.getLikeCount(), postCountInfo.getCommentCount(),false, isLiked);
    }
}
