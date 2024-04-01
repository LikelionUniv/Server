package likelion.univ.domain.post.dto.request;

import likelion.univ.domain.post.dto.enums.PostOrderCondition;

public record GetPostsBySearchTitleCommand(
        PostOrderCondition orderCondition,
        String searchTitle
) {
}
