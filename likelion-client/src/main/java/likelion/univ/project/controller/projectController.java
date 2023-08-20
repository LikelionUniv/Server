package likelion.univ.controller;

import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.service.ImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.project.dto.response.ProjectResponseDto;
import likelion.univ.project.usecase.GetProjectUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/update/{projectId}")
//    @Operation(summary = " .")
    public SuccessResponse<ProjectResponseDto> getProject(@PathVariable("projectId") Long projectId) {
        ProjectResponseDto projectResponseDto = getProjectUsecase.excute(projectId);
        return SuccessResponse.of(projectResponseDto);
    }

    //-----------수정 중 --------//

//    @GetMapping("/v2/project/update/{projectId}")
//    public List<ProjectMember> getProjectV2(@PathVariable("projectId") Long projectId) {
//        List<ProjectMember> result = projectMemberService.getProjectMember(projectId);
//        return result;
//    }

//    @PutMapping("/update/{projectId}")
//    public ProjectSimpleDto updateProject(@PathVariable("projectId") Long projectId, @RequestBody ProjectSimpleDto projectSimpleDto) {
//        projectService.updateProject(projectId, projectSimpleDto);
//        imageService.updateImage(projectId, projectSimpleDto);
//        projectMemberService.updateProjectMember(projectId, projectSimpleDto);
//        return projectService.getProject(projectId);
//    }
//
//    @DeleteMapping("/delete/{projectId}")
//    public void deleteProject(@PathVariable("projectId") Long projectId) {
//        imageService.deleteImage(projectId);
//        projectMemberService.deleteProjectMember(projectId);
//        projectService.deleteProject(projectId);
//    }
}
