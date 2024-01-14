package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.adapter.ProjectImageAdaptor;
import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.adapter.ProjectMemberAdaptor;
import likelion.univ.domain.project.adapter.ProjectTechAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectImage;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.project.dto.response.ProjectListResponseDto;
import likelion.univ.project.dto.response.ProjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetProjectByUsecase {
    private final ProjectAdaptor projectAdaptor;
    private final ProjectImageAdaptor projectImageAdaptor;
    private final UniversityAdaptor universityAdaptor;

    public PageResponse<ProjectListResponseDto> execute(Long ordinal, Pageable pageable){
        long recentOrdinal = projectAdaptor.getCurrentOrdinal();
        if(ordinal > recentOrdinal - 5){
            return getProjectResponseDtos(projectAdaptor.findProject(ordinal, pageable));
        }
        else {
            return getProjectResponseDtos(projectAdaptor.findArchiveProject(recentOrdinal - 5, pageable));
        }
    }

    public PageResponse<ProjectListResponseDto> getProjectResponseDtos(Page<Project> projects) {

        return PageResponse.of(projects.map(project -> ProjectListResponseDto.of(
                project,
                getUniversityName(project),
                projectImageAdaptor.findByProject(project).isEmpty()?
                        null : projectImageAdaptor.findByProject(project).get(0).getImageUrl())));
    }

    public String getUniversityName(Project project) {
        if(project.getUniv() != null)
            return universityAdaptor.findById(project.getUniv().getId()).getName();
        return null;
    }
}
