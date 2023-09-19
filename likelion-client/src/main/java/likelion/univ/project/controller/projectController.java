package likelion.univ.project.controller;

import io.swagger.annotations.Api;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectIdResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.dto.response.UnivResponseDto;
import likelion.univ.project.dto.response.UserResponseDto;
import likelion.univ.project.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/project")
//@Api(tags = {"프로젝트 API"})  //swagger UI에 보일 컨트롤러 이름
public class projectController {

    private final GetProjectUsecase getProjectUsecase;
    private final GetAllPorjectUsecase getAllPorjectUsecase;
    private final CreateProjectUsecase createProjectUsecase;
    private final UpdateProjectUsecase updateProjectUsecase;
    private final DeleteProjectUsecase deleteProjectUsecase;
    private final ArchiveProjectUsecase archiveProjectUsecase;
    private final GetProjectByUsecase getProjectByUsecase;
    private final GetOrdinalUsecase getOrdinalUsecase;
    private final GetUnivUsecase getUnivUsecase;
    private final GetUserUsecase getUserUsecase;

    //-----------프로젝트 한 개 조회 --------//
    @GetMapping("/{projectId}")
//    @Operation(summary = " .")
    public SuccessResponse<ProjectResponseDto> getProject(@PathVariable("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = getProjectUsecase.excute(projectId);
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------프로젝트 목록 --------//
    @GetMapping("/")
    public SuccessResponse<List<ProjectResponseDto>> getAllProject(@PageableDefault(page=0, size=5, sort="id" ,direction = Sort.Direction.DESC) Pageable pageable){
        List<ProjectResponseDto> projectList = getAllPorjectUsecase.excute(pageable);
        return SuccessResponse.of(projectList);
    }

    //--------  기수별 프로젝트 -----//
    @GetMapping("/ordinal/{ordinal}")
    public SuccessResponse<List<ProjectResponseDto>> getProjectByOrdinal(
            @PageableDefault(page=0, size=12, sort="id" ,direction = Sort.Direction.DESC) Pageable pageable,
            @PathVariable int ordinal) {
        int recentOrdinal = getOrdinalUsecase.excute();
        if (ordinal > recentOrdinal - 5) {
            List<ProjectResponseDto> projectListByOrdinal = getProjectByUsecase.excute(ordinal, pageable);
            return SuccessResponse.of(projectListByOrdinal);
        } else {
            List<ProjectResponseDto> projectList = archiveProjectUsecase.excute(ordinal);
            return SuccessResponse.of(projectList);
        }
    }

    //--------- 프로젝트 등록 ------------//
    @PostMapping("/post")
    public SuccessResponse<ProjectIdResponseDto> createProject(@RequestBody ProjectRequestDto projectRequestDto){
        ProjectIdResponseDto projectIdResponseDto = createProjectUsecase.excute(projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 수정 --------//
    @PutMapping("/edit/{projectId}")
    public SuccessResponse<ProjectIdResponseDto> updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectIdResponseDto projectIdResponseDto = updateProjectUsecase.excute(projectId, projectRequestDto);
        return SuccessResponse.of(projectIdResponseDto);
    }

    //-----------프로젝트 삭제 --------//
    @DeleteMapping("/delete/{projectId}")
    public SuccessResponse<Objects> deleteProject(@PathVariable("projectId") Long projectId) {
        deleteProjectUsecase.excute(projectId);
        return SuccessResponse.empty();
    }

    //-----------학교 조회 --------//
    @GetMapping("/university")
    public SuccessResponse<List<UnivResponseDto>> getAllUniv(){
        List<UnivResponseDto> univList = getUnivUsecase.excute();
        return SuccessResponse.of(univList);
    }

    //-----------유저 조회 --------//
    @GetMapping("/users")
    public SuccessResponse<List<UserResponseDto>> getAllUser(){
        List<UserResponseDto> userList = getUserUsecase.excute();
        return SuccessResponse.of(userList);
    }
}
