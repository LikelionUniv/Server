package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.CreateProjectBadRequestException;
import likelion.univ.domain.project.exception.ProjectNotFoundException;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ProjectAdaptor {

    private final ProjectRepository projectRepository;
    public Project findById(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException());
    }

    public Project save(Project project){
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();
        if(startDate.isEqual(endDate) || startDate.isAfter(endDate)){
            throw new CreateProjectBadRequestException();
        }
        try {
            projectRepository.save(project);
        }catch(Exception e) {
            throw new CreateProjectBadRequestException();
        }
        return project;
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public Page<Project> findAll(Pageable pageable){
        return projectRepository.findAll(pageable);
    }
    public Page<Project> findProject(Long ordinal, Pageable pageable){
        return projectRepository.findByOrdinal(ordinal, pageable);
    }
    public Page<Project> findArchiveProject(Long ordinal, Pageable pageable){
        return projectRepository.findArchivePosts(ordinal, pageable);
    }
    public int getCurrentOrdinal(){
        return projectRepository.findLatestOrdinal();
    }

}
