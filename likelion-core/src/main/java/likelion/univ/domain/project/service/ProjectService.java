package likelion.univ.domain.project.service;

import likelion.univ.domain.project.dto.ProjectSimpleDto;
import likelion.univ.domain.project.entity.Image;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectMember;
import likelion.univ.domain.project.repository.ImageRepository;
import likelion.univ.domain.project.repository.ProjectMemberRepository;
import likelion.univ.domain.project.repository.ProjectRepository;
import likelion.univ.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ImageRepository imageRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public ProjectSimpleDto getProject(Long id) {
        Project project = projectRepository.findById(id).get();
        List<Image> images = imageRepository.findByProject(project);
        List<User> members = projectMemberRepository.findByProject(project).stream()
                .map(member -> member.getUser())
                .collect(Collectors.toList());
        ProjectSimpleDto result = new ProjectSimpleDto(project, images, members);
        return result;
    }

    @Transactional
    public void updateProject(Long id, ProjectSimpleDto projectSimpleDto) {
        Project project = projectRepository.findById(id).get();
        project.update(projectSimpleDto);
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id).get();
        if(project != null) {
            projectRepository.delete(project);
        }
    }
}
