package likelion.univ.adminUser.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class FindUnivUseCase {

    private final UniversityAdaptor universityAdaptor;

    public Optional<University> findUniversity(String name){
        Optional<University> university = universityAdaptor.findByName(name);
        return university;
    }
}
