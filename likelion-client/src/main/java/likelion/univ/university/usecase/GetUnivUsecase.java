package likelion.univ.university.usecase;

import java.util.List;
import java.util.stream.Collectors;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.university.dto.response.UnivResponseDto;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetUnivUsecase {

    private final UniversityRepository universityRepository;

    public List<UnivResponseDto> execute() {
        return universityRepository.findAllByStatusIsActive().stream()
                .map(university -> UnivResponseDto.of(university.getName()))
                .collect(Collectors.toList());
    }
}
