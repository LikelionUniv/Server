package likelion.univ.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import likelion.univ.common.response.PageResponse;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.project.dto.response.ProjectListResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.service.ClientProjectQueryService;
import likelion.univ.project.service.ClientProjectService;
import likelion.univ.response.SuccessResponse;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/projects")
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final AuthenticatedUserUtils userUtils;
    private final ClientProjectService projectService;
    private final ClientProjectQueryService projectQueryService;

    @Operation(summary = "프로젝트 조회", description = "프로젝트 id로 프로젝트를 조회했습니다.")
    @GetMapping("/{projectId}")
    public SuccessResponse<ProjectResponseDto> getProject(
            @PathVariable("projectId") Long projectId
    ) {
        ProjectResponseDto projectResponseDto = projectQueryService.getProject(projectId);
        return SuccessResponse.of(projectResponseDto);
    }

    @Operation(summary = "프로젝트 목록", description = "프로젝트 목록을 출력했습니다.")
    @GetMapping
    public SuccessResponse<PageResponse<ProjectResponseDto>> getAllProject(
            @ParameterObject
            @PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        PageResponse<ProjectResponseDto> projectList = projectQueryService.getAllProject(pageable);
        return SuccessResponse.of(projectList);
    }

    @Operation(summary = "기수별 프로젝트", description = "선택한 기수에 따라 프로젝트 목록을 출력했습니다. 최근 5개의 기수보다 이전의 기수는 한번에 보여집니다.")
    @GetMapping("/ordinal/{ordinal}")
    public SuccessResponse<PageResponse<ProjectListResponseDto>> getProjectByOrdinal(
            @ParameterObject
            @PageableDefault(size = 12, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable("ordinal") Long ordinal
    ) {
        PageResponse<ProjectListResponseDto> projectList = projectQueryService.getProjectBy(ordinal, pageable);
        return SuccessResponse.of(projectList);
    }

    @Operation(summary = "프로젝트 등록", description = "새로운 프로젝트를 등록했습니다.")
    @PostMapping
    public SuccessResponse<ProjectIdResponseDto> createProject(
            @Valid @RequestBody ProjectRequestDto projectRequestDto
    ) {
        Long userId = userUtils.getCurrentUserId();
        Long projectId = projectService.create(userId, projectRequestDto);
        return SuccessResponse.of(ProjectIdResponseDto.of(projectId));
    }

    @Operation(summary = "프로젝트 수정", description = "프로젝트의 내용을 수정하였습니다.")
    @PatchMapping("/{projectId}")
    public SuccessResponse<ProjectIdResponseDto> updateProject(
            @PathVariable("projectId") Long projectId,
            @Valid @RequestBody ProjectRequestDto projectRequestDto
    ) {
        projectService.update(projectId, projectRequestDto);
        return SuccessResponse.of(ProjectIdResponseDto.of(projectId));
    }

    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제했습니다.")
    @DeleteMapping("/{projectId}")
    public SuccessResponse deleteProject(
            @PathVariable("projectId") Long projectId
    ) {
        projectService.delete(projectId);
        return SuccessResponse.empty();
    }
}
