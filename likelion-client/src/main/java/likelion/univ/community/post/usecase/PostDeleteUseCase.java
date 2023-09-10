package likelion.univ.community.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.community.post.dto.PostRequestDTO;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.domain.community.post.service.PostService;
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
                .postId(request.getPostId())
                .build();
    }
}
