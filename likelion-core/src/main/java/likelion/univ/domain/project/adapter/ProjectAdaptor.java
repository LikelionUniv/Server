package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.exception.ProjectNotFoundException;
import likelion.univ.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Adaptor
@RequiredArgsConstructor
public class ProjectAdaptor {

    private final ProjectRepository projectRepository;
    public Project findById(Long id){
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException());
    }
//    public Optional<Project> findById(Long id) {
//        return projectRepository.findById(id);
//    }

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

    public List<Project> findAll(){

        return projectRepository.findAll();
    }
}
