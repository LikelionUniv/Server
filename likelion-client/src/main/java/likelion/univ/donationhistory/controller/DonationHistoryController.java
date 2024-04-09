package likelion.univ.donationhistory.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.common.response.PageResponse;
import likelion.univ.donationhistory.dto.response.DonationHistoriesDetailsResponseDto;
import likelion.univ.donationhistory.dto.response.DonationHistoriesSearchResponseDto;
import likelion.univ.donationhistory.usecase.GetDonationHistoryDetailsUsecase;
import likelion.univ.donationhistory.usecase.SearchDonationHistoryUsecase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/v1/donation-history")
@Tag(name = "기부금 게시판", description = "기부금 게시판관련 API입니다.")
public class DonationHistoryController {

    private final SearchDonationHistoryUsecase searchDonationHistoryUsecase;
    private final GetDonationHistoryDetailsUsecase getDonationHistoryDetailsUsecase;

    @Operation(summary = "기부금 게시글 목록 검색", description = "기부금 게시글을 검색합니다. 파라미터로 search를 포함하지 않을 시 전체 조회입니다.")
    @GetMapping
    public SuccessResponse<Object> searchDonationHistories(
            @RequestParam(value = "sort", required = false, defaultValue = "created_date") String sort,
            @RequestParam(value = "search", required = false) String search,
            @ParameterObject @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        PageResponse<DonationHistoriesSearchResponseDto> result = searchDonationHistoryUsecase.execute(sort, search,
                pageable);
        return SuccessResponse.of(result);
    }

    @Operation(summary = "기부금 게시글 상세정보", description = "해당 기부금 게시글의 상세정보를 조회합니다.")
    @GetMapping("/{donationHistoryId}")
    public SuccessResponse<Object> searchDonationHistories(
            @PathVariable("donationHistoryId") Long donationHistoryId
    ) {
        DonationHistoriesDetailsResponseDto result = getDonationHistoryDetailsUsecase.execute(donationHistoryId);
        return SuccessResponse.of(result);
    }
}
