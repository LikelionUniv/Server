package likelion.univ.university.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.university.dto.response.UnivResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetUnivUsecase {

    private final UniversityAdaptor universityAdaptor;

    public List<UnivResponseDto> excute() {
        return universityAdaptor.findAll().stream()
                .map(university -> UnivResponseDto.of(university.getName()))
                .collect(Collectors.toList());
    }
}
