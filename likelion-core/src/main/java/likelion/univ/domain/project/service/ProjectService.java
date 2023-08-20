package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ImageAdapter;
import likelion.univ.domain.project.adapter.ProjectAdapter;
import likelion.univ.domain.project.adapter.ProjectMemberAdapter;
import likelion.univ.domain.project.dto.ProjectSimpleDto;
import likelion.univ.domain.project.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectAdapter projectAdapter;
    private final ImageAdapter imageAdapter;
    private final ProjectMemberAdapter projectMemberAdapter;

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
    public void updateProject(Long id, ProjectSimpleDto projectSimpleDto) {
        Project project = projectAdapter.findById(id).get();
        project.update(projectSimpleDto);
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
