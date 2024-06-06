package likelion.univ.university.service;

import java.util.List;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.university.dto.response.UnivResponseDto;
import likelion.univ.university.dto.response.UniversityDetailResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientUniversityQueryService {

    private final UniversityRepository universityRepository;

    public List<UniversityDetailResponseDto> getLocationUnivDetails(String location) {
        return universityRepository.findByLocationAndStatusIsActive(location)
                .stream()
                .map(UniversityDetailResponseDto::of)
                .toList();
    }

    public List<UniversityDetailResponseDto> getTotalUnivDetails() {
        return universityRepository.findAllByStatusIsActive()
                .stream()
                .map(UniversityDetailResponseDto::of)
                .toList();
    }

    public List<UnivResponseDto> getUniv() {
        return universityRepository.findAllByStatusIsActive()
                .stream()
                .map(university -> UnivResponseDto.of(university.getName()))
                .toList();
    }
}
