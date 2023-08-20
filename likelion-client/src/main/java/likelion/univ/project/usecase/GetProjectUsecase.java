package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ImageAdapter;
import likelion.univ.domain.project.adapter.ProjectAdapter;
import likelion.univ.domain.project.adapter.ProjectMemberAdapter;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.service.ProjectService;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetProjectUsecase {

    private final ProjectAdapter projectAdapter;
    private final ImageAdapter imageAdapter;
    private final ProjectMemberAdapter projectMemberAdapter;
    private final UserAdaptor userAdaptor;

    public ProjectResponseDto excute(Long id) {
        Project project = projectAdapter.findById(id).get();
        List<Image> images = imageAdapter.findByProject(project);
        List<User> users = projectMemberAdapter.findByProject(project).stream()
                .map(projectMember -> projectMember.getUser())
                .map(user -> userAdaptor.findById(user.getId()))
                .collect(Collectors.toList());
        return ProjectResponseDto.of(project, images, users);
    }
}
