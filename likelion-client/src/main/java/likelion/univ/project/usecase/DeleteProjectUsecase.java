package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.service.ImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteProjectUsecase {

    private final ImageService imageService;
    private final ProjectMemberService projectMemberService;
    private  final ProjectService projectService;

    public void excute(Long projectId) {
        imageService.deleteImage(projectId);
        projectMemberService.deleteProjectMember(projectId);
        projectService.deleteProject(projectId);
    }
}
