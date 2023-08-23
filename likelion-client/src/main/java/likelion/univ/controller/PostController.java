package likelion.univ.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.domain.dto.PostDto;
import likelion.univ.domain.dto.common.CommonResponseDto;
import likelion.univ.service.PostService;
import lombok.Getter;
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
    private PostService postService;

    @PostMapping("")
    public ResponseEntity<CommonResponseDto<Object>> createPost(@RequestBody PostDto.Save request) {
        Long userId=1l;
        CommonResponseDto<Object> responseBody = postService.createPost(request, userId);


        return ResponseEntity.ok()
                .body(responseBody);
    }


    @PatchMapping("")
    public ResponseEntity<CommonResponseDto<Object>> updatePost(@RequestBody PostDto.Update request) {
        Long userId=1l;
        CommonResponseDto<Object> responseBody = postService.updatePost(request, userId);

        return ResponseEntity.ok()
                .body(responseBody);
    }


    @DeleteMapping("")
    public ResponseEntity<CommonResponseDto<Object>> deletePost(@RequestBody PostDto.Delete request) {
        Long userId=1l;
        CommonResponseDto<Object> responseBody = postService.deletePost(request, userId);

        return ResponseEntity.ok()
                .body(responseBody);
    }
}
