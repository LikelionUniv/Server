package likelion.univ.project.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.project.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class GetUserUsecase {

    private final UserAdaptor userAdaptor;
    private final UniversityAdaptor universityAdaptor;

    public List<UserResponseDto> excute() {

        return userAdaptor.findAll().stream()
                .map(user -> UserResponseDto.of(user, universityAdaptor.findById(user.getUniversityInfo().getUniversity().getId())))
                .collect(Collectors.toList());
    }
}
