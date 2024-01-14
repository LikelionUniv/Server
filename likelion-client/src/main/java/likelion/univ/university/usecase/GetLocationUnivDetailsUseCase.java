package likelion.univ.university.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.university.dto.response.UniversityDetailResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetLocationUnivDetailsUseCase {
    private final UniversityAdaptor universityAdaptor;

    public List<UniversityDetailResponseDto> execute(String location){
        List<University> universities = universityAdaptor.findByLocation(location);
        return universities.stream().map(univ -> UniversityDetailResponseDto.of(univ))
                .collect(Collectors.toList());
    }
}