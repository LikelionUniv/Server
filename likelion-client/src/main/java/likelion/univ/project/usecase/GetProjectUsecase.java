package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetProjectUsecase {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectImageAdaptor projectImageAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;
    private final UserAdaptor userAdaptor;

    public ProjectResponseDto excute(Long id) {
        Project project = projectAdaptor.findById(id);
        List<Image> images = projectImageAdaptor.findByProject(project);
        List<User> users = projectMemberAdaptor.findByProject(project).stream()
                .map(projectMember -> projectMember.getUser())
                .map(user -> userAdaptor.findById(user.getId()))
                .collect(Collectors.toList());
        return ProjectResponseDto.of(project, images, users);
    }
}
