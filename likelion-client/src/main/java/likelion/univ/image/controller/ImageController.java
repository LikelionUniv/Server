package likelion.univ.image.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.image.dto.response.ImageUrlResponseDto;
import likelion.univ.image.service.ClientImageService;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/image")
@Tag(name = "이미지 업로드", description = "Presigned-url 발급 API")
public class ImageController {

    private final ClientImageService clientImageService;

    @Operation(summary = "project 이미지용입니다.")
    @GetMapping("/project")
    public SuccessResponse<Object> getProjectPresignedUrl(
            @RequestParam("fileNameExtension") String fileNameExtension
    ) {
        ImageUrlResponseDto imageUrlResponseDto = clientImageService.createProjectImagePresignedUrl(
                "project", fileNameExtension
        );
        return SuccessResponse.of(imageUrlResponseDto);
    }

    @Operation(summary = "user 프로필 이미지용입니다.")
    @GetMapping("/user/{userId}")
    public SuccessResponse<Object> getUserPresignedUrl(
            @PathVariable("userId") Long userId,
            @RequestParam("fileNameExtension") String fileNameExtension
    ) {
        ImageUrlResponseDto imageUrlResponseDto = clientImageService.createUserProfileImagePresignedUrl(
                "user", userId, fileNameExtension
        );
        return SuccessResponse.of(imageUrlResponseDto);
    }
}
