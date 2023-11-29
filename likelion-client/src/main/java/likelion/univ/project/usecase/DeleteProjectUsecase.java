package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.ProjectNotAuthorization;
import likelion.univ.domain.project.service.ProjectImageService;
import likelion.univ.domain.project.service.ProjectMemberService;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.project.service.ProjectTechService;
import likelion.univ.domain.user.entity.User;
import likelion.univ.utils.AuthentiatedUserUtils;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteProjectUsecase {

    private final AuthentiatedUserUtils authentiatedUserUtils;
    private final ProjectTechService projectTechService;
    private final ProjectImageService projectImageService;
    private final ProjectMemberService projectMemberService;
    private  final ProjectService projectService;
    private final ProjectAdaptor projectAdaptor;

    public void excute(Long projectId) {
        Project project = projectAdaptor.findById(projectId);
        User user = authentiatedUserUtils.getCurrentUser();

        if(user.getId() != project.getAuthor().getId())
            throw new ProjectNotAuthorization();

        projectTechService.deleteProjectTech(projectId);
        projectImageService.deleteImage(projectId);
        projectMemberService.deleteProjectMember(projectId);
        projectService.deleteProject(projectId);
    }
}
