package likelion.univ.domain.university.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.domain.example.exception.ExampleNotFoundException;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adaptor
@RequiredArgsConstructor
public class UniversityAdaptor {

    private final UniversityRepository universityRepository;

    public Optional<University> findByName(String name){
       return universityRepository.findByName(name);
    }
}
