package likelion.univ.adminUser.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.adminUser.dto.request.NcpSmsRequestDto;
import likelion.univ.adminUser.usecase.SendEmailUseCase;
import likelion.univ.adminUser.usecase.SendNcpSmsUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/likelion")
@Tag(name = "LikelionAdmin", description = "멋쟁이 사자처럼 관리자 API")
public class LikelionAdminController {

    private final SendEmailUseCase sendEmailUseCase;
    private final SendNcpSmsUseCase sendNcpSmsUseCase;


    @Operation(summary = "이메일 전송")
    @PostMapping(value = "/email", consumes = "multipart/form-data")
    public SuccessResponse<Object> sendEmail(SendMailRequestDto sendMailRequestDto)throws IOException {
        sendEmailUseCase.execute(sendMailRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "메신저 전송")
    @PostMapping(value="/messenger")
    public SuccessResponse<Object> sendMessenger(@RequestBody NcpSmsRequestDto ncpSmsRequestDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        sendNcpSmsUseCase.execute(ncpSmsRequestDto);
        return SuccessResponse.empty();
    }

}
