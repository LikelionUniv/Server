package likelion.univ.domain.university.service;

import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public University findByName(String name) {
        return universityRepository.getByName(name);
    }
}
