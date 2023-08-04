package likelion.univ.example.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.example.dto.request.CreateExampleRequestDto;
import likelion.univ.example.dto.response.ExampleInfoResponseDto;
import likelion.univ.example.usecase.CreateExampleUseCase;
import likelion.univ.example.usecase.DeleteExampleUseCase;
import likelion.univ.example.usecase.EditExampleUseCase;
import likelion.univ.example.usecase.GetExampleUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/example")
@Api(tags = {"댓글 API"})
public class ExampleController {
    private final CreateExampleUseCase createExampleUseCase;
    private final EditExampleUseCase editExampleUseCase;
    private final GetExampleUseCase getExampleUseCase;
    private final DeleteExampleUseCase deleteExampleUseCase;

    @GetMapping(value = "/{exampleId}")
    @Operation(summary = "예시를 생성합니다.")
    public SuccessResponse<Object> createExample(@PathVariable Long exampleId){
        //log.info("예시 생성 API 호출 USER = {}", user.getId());
        ExampleInfoResponseDto exampleInfoResponseDto = getExampleUseCase.excute(exampleId);
        return SuccessResponse.of(exampleInfoResponseDto);
    }

    @PostMapping
    @Operation(summary = "예시를 생성합니다.")
    public SuccessResponse<Object> createExample(
            @Validated @RequestBody CreateExampleRequestDto exampleRequestDto){
        //log.info("예시 생성 API 호출 USER = {}", user.getId());
        createExampleUseCase.excute(exampleRequestDto);
        return SuccessResponse.empty();
    }

    @PatchMapping(value = "/{exampleId}")
    @Operation(summary = "예시를 수정합니다.")
    public SuccessResponse<Object> editExample(
            @PathVariable Long exampleId,
            @Validated @RequestBody CreateExampleRequestDto exampleRequestDto){
        //log.info("예시 생성 API 호출 USER = {}", user.getId());
        editExampleUseCase.excute(exampleId,exampleRequestDto);
        return SuccessResponse.empty();
    }

    @DeleteMapping(value = "/{exampleId}")
    @Operation(summary = "예시를 삭제합니다.")
    public SuccessResponse<Object> deleteExample(@PathVariable Long exampleId){
        //log.info("예시 생성 API 호출 USER = {}", user.getId());
        deleteExampleUseCase.excute(exampleId);
        return SuccessResponse.empty();
    }
}
