package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteProjectUsecase {

    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private  final ProjectService projectService;

    public void excute(Long projectId) {
        projectImageService.deleteImage(projectId);
        projectMemberService.deleteProjectMember(projectId);
        projectService.deleteProject(projectId);
    }
}
