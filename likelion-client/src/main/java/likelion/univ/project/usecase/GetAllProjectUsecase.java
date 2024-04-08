package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectImageRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.project.dto.response.ProjectListResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetAllProjectUsecase {

    private final ProjectRepository projectRepository;
    private final ProjectImageRepository projectImageRepository;
    private final UniversityAdaptor universityAdaptor;

    public PageResponse<ProjectResponseDto> execute(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);

        return PageResponse.of(projects.map(project -> ProjectListResponseDto.of(project,
                getUniversityName(project),
                projectImageRepository.findByProject(project).isEmpty() ?
                        null : projectImageRepository.findByProject(project).get(0).getImageUrl())));
    }

    public String getUniversityName(Project project) {
        if (project.getUniv() != null) {
            return universityAdaptor.findById(project.getUniv().getId()).getName();
        }
        return null;
    }
}
