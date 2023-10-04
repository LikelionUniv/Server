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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetAllPorjectUsecase {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectImageAdaptor projectImageAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;
    private final UserAdaptor userAdaptor;

    public List<ProjectResponseDto> excute(int pageNo) {
        List<Project> projects = projectAdaptor.findAll(pageNo);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        for(Project project : projects) {
            List<Image> images = projectImageAdaptor.findByProject(project);
            List<User> users = projectMemberAdaptor.findByProject(project).stream()
                    .map(projectMember -> projectMember.getUser())
                    .map(user -> userAdaptor.findById(user.getId()))
                    .collect(Collectors.toList());
            projectResponseDtos.add(ProjectResponseDto.of(project, images, users));
        }
        return projectResponseDtos;
    }
}
