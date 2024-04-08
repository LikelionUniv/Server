package likelion.univ.university.usecase;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.university.dto.response.UniversityDetailResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetLocationUnivDetailsUsecase {

    private final UniversityRepository universityRepository;

    public List<UniversityDetailResponseDto> execute(String location) {
        List<University> universities = universityRepository.findByLocationAndStatusIsActive(location);
        return universities.stream().map(univ -> UniversityDetailResponseDto.of(univ))
                .collect(Collectors.toList());
    }
}
