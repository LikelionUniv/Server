package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.post.dto.PostRequestDTO;
import likelion.univ.domain.post.dto.PostServiceDTO;
import likelion.univ.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@RequiredArgsConstructor
public class PostDeleteUseCase {

    @Autowired
    private PostService postService;

    public void execute(PostRequestDTO.Delete request) {
        postService.deletePost(buildDTO(request));
        return ;
    }

    PostServiceDTO.DeleteRequest buildDTO(PostRequestDTO.Delete request) {
        return PostServiceDTO.DeleteRequest.builder()
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();
    }
}
