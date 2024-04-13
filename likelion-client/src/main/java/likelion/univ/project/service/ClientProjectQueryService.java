package likelion.univ.project.service;

import java.util.List;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.project.repository.ProjectTechRepository;
import likelion.univ.domain.project.repository.TechRepository;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.project.dto.response.ProjectListResponseDto;
import likelion.univ.project.dto.response.ProjectMemberResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ClientProjectQueryService {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final UniversityRepository universityRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectTechRepository projectTechRepository;
    private final TechRepository techRepository;

    public PageResponse<ProjectResponseDto> getAllProject(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return PageResponse.of(projects.map(project -> ProjectListResponseDto.of(
                project,
                project.getUniv() == null ? null : project.getUniv().getName(),
                projectImageRepository.findByProject(project).isEmpty()
                        ? null
                        : projectImageRepository.findByProject(project).get(0).getImageUrl()))
        );
    }

    public PageResponse<ProjectListResponseDto> getProjectBy(Long ordinal, Pageable pageable) {
        long recentOrdinal = projectRepository.findLatestOrdinal();
        if (ordinal > recentOrdinal - 5) {
            return getProjectResponseDtos(projectRepository.findByOrdinal(ordinal, pageable));
        }
        return getProjectResponseDtos(projectRepository.findArchiveProject(recentOrdinal - 5, pageable));
    }

    public PageResponse<ProjectListResponseDto> getProjectResponseDtos(Page<Project> projects) {
        return PageResponse.of(projects.map(project -> ProjectListResponseDto.of(
                project,
                project.getUniv() == null ? null : project.getUniv().getName(),
                projectImageRepository.findByProject(project).isEmpty()
                        ? null
                        : projectImageRepository.findByProject(project).get(0).getImageUrl()))
        );
    }

    public ProjectResponseDto getProject(Long id) {
        Project project = projectRepository.getById(id);
        String univ = null;
        if (project.getUniv() != null) {
            univ = universityRepository.getById(project.getUniv().getId()).getName();
        }
        List<Tech> projectTeaches = projectTechRepository.findByProject(project).stream()
                .map(ProjectTech::getTech)
                .map(tech -> techRepository.getById(tech.getId()))
                .toList();
        List<ProjectImage> projectImages = projectImageRepository.findByProject(project);
        List<ProjectMemberResponseDto> projectMembers = projectMemberRepository.findByProject(project)
                .stream()
                .map(ProjectMemberResponseDto::of)
                .toList();
        return ProjectResponseDto.of(project, univ, projectTeaches, projectImages, projectMembers);
    }
}
