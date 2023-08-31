package likelion.univ.controller;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.service.ImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.project.dto.request.ProjectRequestDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.usecase.*;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/project")
//swagger UI에 보일 컨트롤러 이름
//@Api(tags = {" API"})
public class projectController {

    private final ProjectService projectService;
    private final ImageService imageService;
    private final ProjectMemberService projectMemberService;

    private final GetProjectUsecase getProjectUsecase;
    private final GetAllPorjectUsecase getAllPorjectUsecase;
    private final CreateProjectUsecase createProjectUsecase;
    private final UpdateProjectUsecase updateProjectUsecase;
    private final DeleteProjectUsecase deleteProjectUsecase;

    //-----------프로젝트 한 개 조회 --------//
    @GetMapping("/update/{projectId}")
//    @Operation(summary = " .")
    public SuccessResponse<ProjectResponseDto> getProject(@PathVariable("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = getProjectUsecase.excute(projectId);
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------프로젝트 목록 --------//
    @GetMapping("/")
    public SuccessResponse<List<ProjectResponseDto>> getAllProject(){
        List<ProjectResponseDto> projectList = getAllPorjectUsecase.excute();
        return SuccessResponse.of(projectList);
    }

    //--------- 프로젝트 등록 ------------//
    @PostMapping("/post")
    public SuccessResponse<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto projectRequestDto){

        ProjectResponseDto projectResponseDto = createProjectUsecase.excute(projectRequestDto);
//        return new ProjectResponseDto(project.getId(),"프로젝트 등록 성공");
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------프로젝트 수정 --------//
    @PutMapping("/update/{projectId}")
    public SuccessResponse<ProjectResponseDto> updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectRequestDto projectRequestDto) {
        ProjectResponseDto projectResponseDto = updateProjectUsecase.excute(projectId, projectRequestDto);
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------프로젝트 삭제 --------//
    @DeleteMapping("/delete/{projectId}")
    public SuccessResponse<Objects> deleteProject(@PathVariable("projectId") Long projectId) {
        deleteProjectUsecase.excute(projectId);
        return SuccessResponse.empty();
    }
}
