package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.post.dto.PostSimpleResponseDto;
import likelion.univ.domain.post.dto.PostUpdateServiceDto;
import likelion.univ.post.dto.PostRequestDTO;
import likelion.univ.domain.post.dto.PostServiceDTO;
import likelion.univ.domain.post.service.PostService;
import likelion.univ.post.dto.PostUpdateRequestDto;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


@UseCase
@RequiredArgsConstructor
public class PostUpdateUseCase {
    private final PostService postService;
    private final AuthentiatedUserUtils userUtils;

    public PostSimpleResponseDto execute(Long postId, PostUpdateRequestDto request) {
        return postService.editPost(buildDTO(postId, request));
    }

    private PostUpdateServiceDto buildDTO(Long postId, PostUpdateRequestDto request) {
        return PostUpdateServiceDto.builder()
                .userId(userUtils.getCurrentUserId())
                .postId(postId)
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .body(request.getBody())
                .build();
    }
}
