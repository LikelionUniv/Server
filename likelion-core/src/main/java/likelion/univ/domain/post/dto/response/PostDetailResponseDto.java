package likelion.univ.domain.post.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import likelion.univ.domain.comment.dto.ParentCommentDetailResponseDto;
import lombok.Builder;

import java.util.List;

public record PostDetailResponseDto(
        Long postId,
        Long authorId,
        String authorName,
        String authorProfileImageUrl,
        String universityName,
        Boolean isMyPost, // 내 게시글인지 (securityContext.userId == post.authorId)
        Boolean hasFollowedAuthor, // 내가 팔로우한 글쓴이인지 (followlist.contains(post.authorId))
        Boolean isLikedPost, // 내가 좋아요 한 게시글인지
        String title,
        String body,
        List<ParentCommentDetailResponseDto> comments
) {
    @Builder
    public PostDetailResponseDto(Long postId, Long authorId, String authorName, String authorProfileImageUrl, String universityName, Boolean isMyPost, Boolean hasFollowedAuthor, Boolean isLikedPost, String title, String body, List<ParentCommentDetailResponseDto> comments) {
        this.postId = postId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorProfileImageUrl = authorProfileImageUrl;
        this.universityName = universityName;
        this.isMyPost = isMyPost;
        this.hasFollowedAuthor = hasFollowedAuthor;
        this.isLikedPost = isLikedPost;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }
}
