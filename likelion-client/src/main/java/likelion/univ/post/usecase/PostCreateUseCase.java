package likelion.univ.post.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.post.dto.PostRequestDTO;
import likelion.univ.domain.post.dto.PostServiceDTO;
import likelion.univ.domain.post.service.PostService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
@RequiredArgsConstructor
public class PostCreateUseCase {


    @Autowired
    private PostService postService;
    @Autowired
    private UserAdaptor userAdaptor;
    public PostServiceDTO.ResponseDTO execute(PostRequestDTO.Save request) {
        //user 인자 추가
        PostServiceDTO.ResponseDTO response = postService.createPost(buildDTO(request));
        return response;
    }

    PostServiceDTO.CreateRequest buildDTO(PostRequestDTO.Save request) {
        return PostServiceDTO.CreateRequest.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .user(userAdaptor.findById(request.getUserId()))
                .thumbnail(request.getThumbnail())
                .mainCategory(request.getMainCategory())
                .subCategory(request.getSubCategory())
                .build();
    }
}
