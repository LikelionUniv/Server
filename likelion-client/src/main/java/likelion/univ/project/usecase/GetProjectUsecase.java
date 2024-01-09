package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.response.ProjectMemberResponseDto;
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
    private final ProjectTechAdaptor projectTechAdaptor;
    private final UserAdaptor userAdaptor;
    private final UniversityAdaptor universityAdaptor;

    public ProjectResponseDto execute(Long id) {
        Project project = projectAdaptor.findById(id);
        String univ = null;
        if(project.getUniv() != null)
            univ = universityAdaptor.findById(project.getUniv().getId()).getName();

        List<Tech> projectTeches = projectTechAdaptor.findByProject(project).stream()
                .map(projectTech -> projectTech.getTech())
                .map(tech -> projectTechAdaptor.findById(tech.getId()))
                .collect(Collectors.toList());

        List<ProjectImage> projectImages = projectImageAdaptor.findByProject(project);

        List<ProjectMemberResponseDto> projectMembers = projectMemberAdaptor.findByProject(project)
                .stream().map(projectMember -> ProjectMemberResponseDto.of(projectMember)).toList();
        return ProjectResponseDto.of(project, univ, projectTeches, projectImages, projectMembers);
    }
}
