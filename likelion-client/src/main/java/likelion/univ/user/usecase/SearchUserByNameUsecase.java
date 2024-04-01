package likelion.univ.user.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.common.response.SliceResponse;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.user.dto.response.UserSearchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@UseCase
@RequiredArgsConstructor
public class SearchUserByNameUsecase {

    private final UserAdaptor userAdaptor;

    public SliceResponse execute(String name, Pageable pageable) {
        Slice<User> userSlice = userAdaptor.searchByName(name, pageable);
        return SliceResponse.of(userSlice.map(u -> UserSearchResultDto.of(u)));
    }
}
