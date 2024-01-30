package likelion.univ.adminUser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminUser.dto.response.UserInfoResponseDto;
import likelion.univ.adminUser.usecase.FindAllByHeadqueatersUseCase;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/headquaters")
@RequiredArgsConstructor
@Tag(name = "UserByHeadquaters", description = "본사에 의한 사용자 관련 API")
public class HeadquatersController {

    private final FindAllByHeadqueatersUseCase findAllByHeadqueatersUseCase;

    @Operation(summary = "회원 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<PageResponse<UserInfoResponseDto>> findUsersByUnivOfUser(
            @ParameterObject @PageableDefault(size=12, page = 0,
                    sort="createdDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(required = false) Role role, @RequestParam(required = false) String univName) {
        PageResponse<UserInfoResponseDto> response = findAllByHeadqueatersUseCase.execute(role, univName, pageable);
        return SuccessResponse.of(response);
    }

}
