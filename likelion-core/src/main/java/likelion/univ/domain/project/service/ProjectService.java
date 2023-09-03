package likelion.univ.domain.project.service;

import likelion.univ.domain.project.adapter.ProjectAdaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.enums.Output;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true) //읽기 전용 모드
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectAdaptor projectAdaptor;

    @Transactional
    public Project createProject(String thon, Output outPut, String serviceName, int ordinal, String univ, LocalDate startDate,
                                 LocalDate endDate, String tech, String description, String content, String projectUrl)
    {
        // 유저 권한 설정 필요 ?
        Project project = Project.builder()
                .thon(thon)
                .outPut(outPut)
                .serviceName(serviceName)
                .ordinal(ordinal)
                .univ(univ)
                .startDate(startDate)
                .endDate(endDate)
                .tech(tech)
                .description(description)
                .content(content)
                .projectUrl(projectUrl)
                .build();
        Project savedProject = projectAdaptor.save(project);

        return savedProject;
    }

    @Transactional
    public void updateProject(Long id, String thon, Output output, String serviceName, int ordinal, String univ, LocalDate startDate, LocalDate endDate, String tech, String description, String content, String projectUrl) {
        Project project = projectAdaptor.findById(id);
        project.update(thon, output, serviceName, ordinal, univ, startDate, endDate, tech, description, content, projectUrl);
        projectAdaptor.save(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectAdaptor.findById(id);
        if(project != null) {
            projectAdaptor.delete(project);
        }
    }
}
