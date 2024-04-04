package likelion.univ.domain.post.dto.response;

import java.time.LocalDateTime;
import likelion.univ.common.processor.DateCustomFormatter;
import likelion.univ.domain.post.dto.enums.MainCategory;
import likelion.univ.domain.post.dto.enums.SubCategory;
import lombok.Builder;

@Builder
public record PostDetailData(
        Long postId,
        MainCategory mainCategory,
        SubCategory subCategory,
        Long authorId,
        String authorName,
        String authorProfileImageUrl,
        Long authorOrdinal,
        String universityName,
        Boolean isFollowedAuthor, // 내가 팔로우한 글쓴이인지 (followlist.contains(post.authorId))
        Boolean isLikedPost, // 내가 좋아요 한 게시글인지
        Integer likeCount,
        String title,
        String body,
        LocalDateTime createdDate
) {

    public String getFormattedDate() {
        return DateCustomFormatter.format(this.createdDate);
    }
}
