package likelion.univ.domain.post.dto.response;

import likelion.univ.domain.comment.dto.response.ChildCommentData;
import likelion.univ.domain.comment.dto.response.ParentCommentData;
import lombok.Builder;

import java.util.List;

@Builder
public record PostDetailData(
        Long postId,
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
        List<ParentCommentData> parentComments,
        List<ChildCommentData> childComments
) {
}
