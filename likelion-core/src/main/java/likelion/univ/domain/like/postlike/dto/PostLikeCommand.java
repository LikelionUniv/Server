package likelion.univ.domain.like.postlike.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public record PostLikeCommand(
        Long postId,
        Long loginUserId
) {

}
