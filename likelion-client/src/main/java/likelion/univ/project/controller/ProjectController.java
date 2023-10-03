package likelion.univ.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.usecase.*;
import likelion.univ.response.SuccessResponse;
import likelion.univ.university.usecase.GetUnivUsecase;
import likelion.univ.university.usecase.GetUserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    private final ArchiveProjectUsecase archiveProjectUsecase;
    private final GetProjectByUsecase getProjectByUsecase;
    private final GetOrdinalUsecase getOrdinalUsecase;

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
    public SuccessResponse<List<ProjectResponseDto>> getAllProject(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo
    ){
        List<ProjectResponseDto> projectList = getAllPorjectUsecase.excute(pageNo);
        return SuccessResponse.of(projectList);
    }

    //--------  기수별 프로젝트 -----//
    @GetMapping("/ordinal/{ordinal}")
    @Operation(summary = "기수별 프로젝트", description = "선택한 기수에 따라 프로젝트 목록을 출력했습니다. 최근 5개의 기수보다 이전의 기수는 한번에 보여집니다.")
    public SuccessResponse<List<ProjectResponseDto>> getProjectByOrdinal(
            @RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
            @PathVariable Long ordinal) {
        int recentOrdinal = getOrdinalUsecase.excute();
        if (ordinal > recentOrdinal - 5) {
            List<ProjectResponseDto> projectListByOrdinal = getProjectByUsecase.excute(ordinal, pageNo);
            return SuccessResponse.of(projectListByOrdinal);
        } else {
            List<ProjectResponseDto> projectList = archiveProjectUsecase.excute(ordinal);
            return SuccessResponse.of(projectList);
        }
    }

    //--------- 프로젝트 등록 ------------//
    @PostMapping("/")
    @Operation(summary = "프로젝트 등록", description = "새로운 프로젝트를 등록했습니다.")
    public SuccessResponse<ProjectIdResponseDto> createProject(@RequestBody ProjectRequestDto projectRequestDto){
        ProjectIdResponseDto projectIdResponseDto = createProjectUsecase.excute(projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 수정 --------//
    @PatchMapping("/{projectId}/edit")
    @Operation(summary = "프로젝트 수정", description = "프로젝트의 내용을 수정하였습니다.")
    public SuccessResponse<ProjectIdResponseDto> updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectIdResponseDto projectIdResponseDto = updateProjectUsecase.excute(projectId, projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 삭제 --------//
    @DeleteMapping("/{projectId}/delete")
    @Operation(summary = "프로젝트 삭제", description = "프로젝트를 삭제했습니다.")
    public SuccessResponse<Objects> deleteProject(@PathVariable("projectId") Long projectId) {
        deleteProjectUsecase.excute(projectId);
        return SuccessResponse.empty();
    }
}
