package likelion.univ.adminuser.controller;

import static org.springframework.data.domain.Sort.Direction.DESC;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminuser.dto.response.UserInfoResponseDto;
import likelion.univ.adminuser.service.HeadquarterUserService;
import likelion.univ.common.response.PageResponse;
import likelion.univ.domain.user.entity.Role;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("adminuser.controller.HeadquarterUserController")
@RequestMapping("/v1/headquarters")
@RequiredArgsConstructor
@Tag(name = "UserByHeadquarters", description = "본사에 의한 사용자 관련 API")
public class HeadquarterUserController {

    private final HeadquarterUserService headquarterUserService;

    @Operation(summary = "회원 전체 조회")
    @GetMapping("/users")
    public SuccessResponse<PageResponse<UserInfoResponseDto>> findUsersByHeadquaters(
            @ParameterObject
            @PageableDefault(size = 10, page = 0, sort = "createdDate", direction = DESC)
            Pageable pageable,
            @RequestParam(value = "role", required = false) Role role,
            @RequestParam(value = "univName", required = false) String univName
    ) {
        PageResponse<UserInfoResponseDto> response = headquarterUserService.findAll(role, univName, pageable);
        return SuccessResponse.of(response);
    }
}
