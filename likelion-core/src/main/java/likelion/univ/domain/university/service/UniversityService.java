package likelion.univ.domain.university.service;

import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityAdaptor universityAdaptor;

    public University findByName(String name) {
        return universityAdaptor.findByName(name);
    }
}
