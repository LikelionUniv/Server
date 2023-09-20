package likelion.univ.domain.university.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class UniversityAdaptor {
    private final UniversityRepository universityRepository;

    public University findByName(String name){
        return universityRepository.findByName(name)
                .orElseThrow(() -> new UniversityNotFoundException());
    }

    public List<University> findAll() {
        return universityRepository.findAll();
    }

    public University findById(Long id) {
        return universityRepository.findById(id).get();
    }
}
