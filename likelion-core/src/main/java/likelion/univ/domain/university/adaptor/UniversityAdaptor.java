package likelion.univ.domain.university.adaptor;

import java.util.List;
import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.entity.UniversityStatus;
import likelion.univ.domain.university.exception.UniversityNotFoundException;
import likelion.univ.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class UniversityAdaptor {

    private final UniversityRepository universityRepository;

    public University findByName(String name) {
        return universityRepository.findByName(name)
                .orElseThrow(() -> new UniversityNotFoundException());
    }

    public List<University> findAll() {
        return universityRepository.findAllByUniversityStatus(UniversityStatus.ACTIVE);
    }

    public List<University> findByLocation(String location) {
        return universityRepository.findByLocationAndUniversityStatus(location, UniversityStatus.ACTIVE);
    }

    public University findById(Long id) {
        return universityRepository.findById(id).get();
    }
}
