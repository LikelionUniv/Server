package likelion.univ.community.post.controller;


import likelion.univ.community.post.dto.PostRequestDTO;
import likelion.univ.community.post.usecase.PostCreateUseCase;
import likelion.univ.community.post.usecase.PostDeleteUseCase;
import likelion.univ.community.post.usecase.PostEditUseCase;
import likelion.univ.community.post.usecase.PostRetrieveUseCase;
import likelion.univ.domain.community.post.dto.PostServiceDTO;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/community/post")
public class PostController {

    @Autowired
    private PostCreateUseCase postCreateUseCase;

    @Autowired
    private PostEditUseCase postEditUseCase;

    @Autowired
    private PostDeleteUseCase postDeleteUseCase;

    @Autowired
    private PostRetrieveUseCase postRetrieveUseCase;

    @PostMapping("")
    public SuccessResponse createPost(@RequestBody PostRequestDTO.Save request) {

        PostServiceDTO.ResponseDTO response = postCreateUseCase.execute(request);

        return SuccessResponse.of(response);
    }

    @GetMapping("")
    public SuccessResponse retrievePostPaging(@RequestParam Integer page,@RequestParam Integer limit) {

        List<PostServiceDTO.Retrieve> response = postRetrieveUseCase.execute(page,limit);

        return SuccessResponse.of(response);
    }

    @PatchMapping("")
    public SuccessResponse editPost(@RequestBody PostRequestDTO.Edit request) {

        PostServiceDTO.ResponseDTO response = postEditUseCase.execute(request);

        return SuccessResponse.of(response);
    }

    @DeleteMapping("")
    public SuccessResponse deletePost(@RequestBody PostRequestDTO.Delete request) {

        postDeleteUseCase.execute(request);

        return SuccessResponse.empty();
    }


}
