package likelion.univ.donation_history.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.donation_history.dto.response.DonationHistoriesDetailsResponseDto;
import likelion.univ.donation_history.usecase.GetDonationHistoryDetailsUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/donation_history")
@Tag(name = "기부금 게시판", description = "기부금 게시판관련 API입니다.")
public class DonationHistoryController {
    private final GetDonationHistoryDetailsUseCase getDonationHistoryDetailsUseCase;

//    @Operation(summary = "기부금 게시글 목록 검색", description = "기부금 게시글을 검색합니다. 파라미터로 search를 포함하지 않을 시 전체 조회입니다.")
//    @GetMapping("")
//    public SuccessResponse<Object> searchDonationHistories(@RequestParam(value="sort", required = false, defaultValue="created_date") String sort,
//                                              @RequestParam(value="search", required = false) String search,
//                                              @ParameterObject @PageableDefault(size = 6, page = 1) Pageable pageable){
//
//        return SuccessResponse.of(profileDetailsDto);
//    }

    @Operation(summary = "기부금 게시글 상세정보", description = "해당 기부금 게시글의 상세정보를 조회합니다.")
    @GetMapping("/{donationHistoryId}")
    public SuccessResponse<Object> searchDonationHistories(@PathVariable Long donationHistoryId){
        DonationHistoriesDetailsResponseDto result = getDonationHistoryDetailsUseCase.execute(donationHistoryId);
        return SuccessResponse.of(result);
    }
}
