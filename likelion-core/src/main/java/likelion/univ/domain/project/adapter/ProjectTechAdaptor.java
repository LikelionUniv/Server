package likelion.univ.domain.project.adapter;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.project.entity.Project;
import likelion.univ.domain.project.entity.ProjectTech;
import likelion.univ.domain.project.entity.Tech;
import likelion.univ.domain.project.exception.ProjectNotFoundException;
import likelion.univ.domain.project.repository.ProjectTechRepository;
import likelion.univ.domain.project.repository.TechRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ProjectTechAdaptor {
    private final TechRepository techRepository;
    private final ProjectTechRepository projectTechRepository;

    public void saveTech(Tech tech){techRepository.save(tech);}
    public Tech findByName(String techName){
        return techRepository.findTechByTechName(techName);
    }
    public Tech findById(Long id){return techRepository.findById(id).orElseThrow(()-> new ProjectNotFoundException());}
    public List<ProjectTech> findByProject(Project project) {
        return projectTechRepository.findByProject(project);
    }

    public void saveAll(List<ProjectTech> projectTechList) {
        projectTechRepository.saveAll(projectTechList);
    }

    public void deleteByProject(Project project) {
        projectTechRepository.deleteByProject(project);
    }

    public void save(ProjectTech projectTech){
        projectTechRepository.save(projectTech);
    }

}
