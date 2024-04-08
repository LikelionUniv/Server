package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.user.dto.response.UserPageProjectsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@UseCase
@RequiredArgsConstructor
public class GetUserProjectsUsecase {

    private final ProjectRepository projectRepository;

    public PageResponse<UserPageProjectsDto> execute(Long userId, Pageable pageable) {
        Page<Project> projects = projectRepository.findByProjectMember(userId, pageable);
        return PageResponse.of(projects.map(p -> UserPageProjectsDto.of(p)));
    }
}
