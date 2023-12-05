package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
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

    public List<ProjectResponseDto> excute(Pageable pageable) {
        List<Project> projects = projectAdaptor.findAll(pageable);
        List<ProjectResponseDto> projectResponseDtos = new ArrayList<>();
        String univ = null;
        for(Project project : projects) {
            if(project.getUniv() != null)
                univ = universityAdaptor.findById(project.getUniv().getId()).getName();
            List<Tech> projectTeches = projectTechAdaptor.findByProject(project).stream()
                    .map(projectTech -> projectTech.getTech())
                    .map(tech -> projectTechAdaptor.findById(tech.getId()))
                    .collect(Collectors.toList());
            List<Image> images = projectImageAdaptor.findByProject(project);
            List<User> users = projectMemberAdaptor.findByProject(project).stream()
                    .map(projectMember -> projectMember.getUser())
                    .map(user -> userAdaptor.findById(user.getId()))
                    .collect(Collectors.toList());
            projectResponseDtos.add(ProjectResponseDto.of(project, univ, projectTeches, images, users));
        }
        return projectResponseDtos;
    }
}
