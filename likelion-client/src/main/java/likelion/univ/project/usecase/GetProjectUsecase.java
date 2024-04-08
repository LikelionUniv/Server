package likelion.univ.project.usecase;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.project.repository.ProjectTechRepository;
import likelion.univ.domain.project.repository.TechRepository;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.project.dto.response.ProjectMemberResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetProjectUsecase {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectTechRepository projectTechRepository;
    private final TechRepository techRepository;
    private final UniversityRepository universityRepository;

    public ProjectResponseDto execute(Long id) {
        Project project = projectRepository.getById(id);
        String univ = null;
        if (project.getUniv() != null) {
            univ = universityRepository.getById(project.getUniv().getId()).getName();
        }

        List<Tech> projectTeches = projectTechRepository.findByProject(project).stream()
                .map(projectTech -> projectTech.getTech())
                .map(tech -> techRepository.getById(tech.getId()))
                .collect(Collectors.toList());

        List<ProjectImage> projectImages = projectImageRepository.findByProject(project);

        List<ProjectMemberResponseDto> projectMembers = projectMemberRepository.findByProject(project)
                .stream().map(projectMember -> ProjectMemberResponseDto.of(projectMember)).toList();
        return ProjectResponseDto.of(project, univ, projectTeches, projectImages, projectMembers);
    }
}
