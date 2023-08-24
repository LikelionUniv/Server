package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ImageAdapter;
import likelion.univ.domain.project.adapter.ProjectAdapter;
import likelion.univ.domain.project.adapter.ProjectMemberAdapter;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectAdapter projectAdapter;
    private final ImageAdapter imageAdapter;
    private final ProjectMemberAdapter projectMemberAdapter;

    @Transactional
//    public Project createProject(ProjectRequestDto projectRequestDto) {
//        // 유저 권한 설정 필요 ?
//        Project project = projectAdapter.save(projectRequestDto.toEntity());
//
//        return project;
//    }

    public List<Project> findProjectAll(){
        return projectAdapter.findAll();
    }

//    public ProjectSimpleDto getProject(Long id) {
//        Project project = projectAdapter.findById(id);
//        List<Image> images = imageAdapter.findByProject(project);
//        List<User> members = projectMemberAdapter.findByProject(project).stream()
//                .map(member -> member.getUser())
//                .collect(Collectors.toList());
//        ProjectSimpleDto result = new ProjectSimpleDto(project, images, members);
//        return result;
//    }

    @Transactional
    public void updateProject(Long id, String thon, Output output, String serviceName, int ordinal, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String projectUrl) {
        Project project = projectAdapter.findById(id).get();
        project.update(thon, output, serviceName, ordinal, univ, startDate, endDate, tech, description, content, projectUrl);
        projectAdapter.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectAdapter.findById(id).get();
        if(project != null) {
            projectAdapter.delete(project);
        }
    }
}
