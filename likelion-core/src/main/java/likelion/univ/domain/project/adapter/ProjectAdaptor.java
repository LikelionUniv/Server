package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
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

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

//    public void save(Project project) {
//        projectRepository.save(project);
//    }

    public Project save(Project project){
        projectRepository.save(project);
        return project;
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public Page<Project> findAll(Pageable pageable){

        return projectRepository.findAll(pageable);
    }
}
