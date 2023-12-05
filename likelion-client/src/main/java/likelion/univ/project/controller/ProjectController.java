package likelion.univ.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.response.PageResponse;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/project")
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final GetProjectUsecase getProjectUsecase;
    private final GetAllPorjectUsecase getAllPorjectUsecase;
    private final CreateProjectUsecase createProjectUsecase;
    private final UpdateProjectUsecase updateProjectUsecase;
    private final DeleteProjectUsecase deleteProjectUsecase;
    private final GetProjectByUsecase getProjectByUsecase;

    //-----------프로젝트 한 개 조회 --------//
    @GetMapping("/{projectId}")
    @Operation(summary = "프로젝트 조회", description = "프로젝트 id로 프로젝트를 조회했습니다.")
    public SuccessResponse<ProjectResponseDto> getProject(@PathVariable("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = getProjectUsecase.excute(projectId);
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------프로젝트 목록 --------//
    @GetMapping("/")
    @Operation(summary = "프로젝트 목록", description = "프로젝트 목록을 출력했습니다.")
    public SuccessResponse<Object> getAllProject(
            @PageableDefault(size=12, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        PageResponse<ProjectResponseDto> projectList = getAllPorjectUsecase.excute(pageable);
        return SuccessResponse.of(projectList);
    }

    //--------  기수별 프로젝트 -----//
    @GetMapping("/ordinal/{ordinal}")
    @Operation(summary = "기수별 프로젝트", description = "선택한 기수에 따라 프로젝트 목록을 출력했습니다. 최근 5개의 기수보다 이전의 기수는 한번에 보여집니다.")
    public SuccessResponse<Object> getProjectByOrdinal(
            @PageableDefault(size=12, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable Long ordinal) {
        PageResponse<ProjectResponseDto> projectList = getProjectByUsecase.excute(ordinal,pageable);
        return SuccessResponse.of(projectList);
    }

    //--------- 프로젝트 등록 ------------//
    @PostMapping("/")
    @Operation(summary = "프로젝트 등록", description = "새로운 프로젝트를 등록했습니다.")
    public SuccessResponse<ProjectIdResponseDto> createProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
        ProjectIdResponseDto projectIdResponseDto = createProjectUsecase.excute(projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 수정 --------//
    @PatchMapping("/{projectId}")
    @Operation(summary = "프로젝트 수정", description = "프로젝트의 내용을 수정하였습니다.")
    public SuccessResponse<ProjectIdResponseDto> updateProject(@PathVariable("projectId") Long projectId, @Valid @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectIdResponseDto projectIdResponseDto = updateProjectUsecase.excute(projectId, projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 삭제 --------//
    @DeleteMapping("/{projectId}")
    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제했습니다.")
    public SuccessResponse<Objects> deleteProject(@PathVariable("projectId") Long projectId) {
        deleteProjectUsecase.excute(projectId);
        return SuccessResponse.empty();
    }
}
