package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.CreateProjectBadRequestException;
import likelion.univ.domain.project.exception.ProjectNotFoundException;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Adaptor
@RequiredArgsConstructor
public class ProjectAdaptor {

    private final ProjectRepository projectRepository;
    public Project findById(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException());
    }

    public Project save(Project project){
        try {
            projectRepository.save(project);
        }catch(Exception e) {
            System.out.println("message");
            System.out.println(e.getMessage());
            throw new CreateProjectBadRequestException();
        }
//        projectRepository.save(project);
        return project;
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public Page<Project> findAll(Pageable pageable){

        return projectRepository.findAll(pageable);
    }
    public Page<Project> findProject(int ordinal, Pageable pageable){
        return projectRepository.findByOrdinal(ordinal,pageable);
    }
    public List<Project> findArchiveProject(int ordinal){
        return projectRepository.findArchivePosts(ordinal);
    }
    public int getCurrentOrdinal(){
        return projectRepository.findLatestOrdinal();
    }

}
