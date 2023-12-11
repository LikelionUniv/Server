package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetAllPorjectUsecase {

    private final ProjectAdaptor projectAdaptor;
    private final ProjectTechAdaptor projectTechAdaptor;
    private final ProjectImageAdaptor projectImageAdaptor;
    private final ProjectMemberAdaptor projectMemberAdaptor;
    private final UserAdaptor userAdaptor;
    private final UniversityAdaptor universityAdaptor;

    public PageResponse<ProjectResponseDto> excute(Pageable pageable) {
        Page<Project> projects = projectAdaptor.findAll(pageable);

        return PageResponse.of(projects.map(project -> ProjectResponseDto.of(
                project,
                getUniversityName(project),
                projectTechAdaptor.findByProject(project).stream()
                        .map(projectTech -> projectTech.getTech())
                        .map(tech -> projectTechAdaptor.findById(tech.getId()))
                        .collect(Collectors.toList()),
                projectImageAdaptor.findByProject(project),
                projectMemberAdaptor.findByProject(project).stream()
                        .map(projectMember -> projectMember.getUser())
                        .map(user -> userAdaptor.findById(user.getId()))
                        .collect(Collectors.toList())))
        );
    }

    public String getUniversityName(Project project) {
        if(project.getUniv() != null)
            return universityAdaptor.findById(project.getUniv().getId()).getName();
        return null;
    }
}
