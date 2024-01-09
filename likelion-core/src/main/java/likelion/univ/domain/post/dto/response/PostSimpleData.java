package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import likelion.univ.domain.post.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
public record PostSimpleData(
    Long postId,
    Long authorId,
    MainCategory mainCategory,
    SubCategory subCategory,
    String authorName,
    String authorProfileImageUrl,
    String title,
    String body,
    String thumbnailUrl,
    LocalDateTime createdDate

) {
    @QueryProjection
    public PostSimpleData(Long postId, Long authorId, MainCategory mainCategory, SubCategory subCategory, String authorName, String authorProfileImageUrl, String title, String body, String thumbnailUrl, LocalDateTime createdDate) {
        body = PostSimpleData.parseBody(body);

        this.postId = postId;
        this.authorId = authorId;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.authorName = authorName;
        this.authorProfileImageUrl = authorProfileImageUrl;
        this.title = title;
        this.body = body;
        this.thumbnailUrl = thumbnailUrl;
        this.createdDate = createdDate;
    }

    public static PostSimpleData of(Post post) {
        String body = PostSimpleData.parseBody(post.getBody());

        return new PostSimpleData(
                post.getId(),
                post.getAuthor().getId(),
                post.getMainCategory(),
                post.getSubCategory(),
                post.getAuthor().getProfile().getName(),
                post.getAuthor().getProfile().getProfileImage(),
                post.getTitle(),
                body,
                post.getThumbnail(),
                post.getCreatedDate()
        );
    }

    private static String parseBody(String body) {
        String noImgBody = PostSimpleData.removeImageTag(body);
        return PostSimpleData.shortenString(noImgBody);
    }
    public static String removeImageTag(String imageIncluded) {
        String imageExcluded = imageIncluded.replaceAll("<p><img\\s.*?></p>", "");
        return imageExcluded;
    }
    public static String shortenString(String longString) {
        if (longString.length() < 300) {
            return longString;
        }
        return longString.substring(0, 300);
    }

    public String getFormattedDate() {
        return this.createdDate().format(DateTimeFormatter.ofPattern("yyyy. MM. dd"));
    }


}
