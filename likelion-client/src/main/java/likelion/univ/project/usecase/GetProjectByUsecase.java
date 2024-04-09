package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.project.dto.response.ProjectListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetProjectByUsecase {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final UniversityRepository universityRepository;

    public PageResponse<ProjectListResponseDto> execute(Long ordinal, Pageable pageable) {
        long recentOrdinal = projectRepository.findLatestOrdinal();
        if (ordinal > recentOrdinal - 5) {
            return getProjectResponseDtos(projectRepository.findByOrdinal(ordinal, pageable));
        } else {
            return getProjectResponseDtos(projectRepository.findArchiveProject(recentOrdinal - 5, pageable));
        }
    }

    public PageResponse<ProjectListResponseDto> getProjectResponseDtos(Page<Project> projects) {

        return PageResponse.of(projects.map(project -> ProjectListResponseDto.of(
                project,
                getUniversityName(project),
                projectImageRepository.findByProject(project).isEmpty() ?
                        null : projectImageRepository.findByProject(project).get(0).getImageUrl())));
    }

    public String getUniversityName(Project project) {
        if (project.getUniv() != null) {
            return universityRepository.getById(project.getUniv().getId()).getName();
        }
        return null;
    }
}
