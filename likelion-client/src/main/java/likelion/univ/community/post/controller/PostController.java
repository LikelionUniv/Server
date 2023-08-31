package likelion.univ.community.post.controller;


import likelion.univ.community.post.dto.PostRequestDTO;
import likelion.univ.community.post.usecase.PostCreateUseCase;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.domain.community.post.service.PostService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/community/post")
public class PostController {

    @Autowired
    private PostCreateUseCase postCreateUseCase;

    @PostMapping("")
    public SuccessResponse createPost(@RequestBody PostRequestDTO.Save request) {

        PostServiceDTO.CreateResponse response = postCreateUseCase.execute(request);

        return SuccessResponse.of(response);
    }


}
