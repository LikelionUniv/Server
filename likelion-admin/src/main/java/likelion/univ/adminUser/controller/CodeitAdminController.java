package likelion.univ.adminUser.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.adminUser.dto.request.SendMsgRequestDto;
import likelion.univ.adminUser.usecase.SendAdsUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/codeit")
@Api(tags = {"코드잇"})
public class CodeitAdminController {

    private final SendAdsUseCase sendAdsUseCase;


    @Operation(summary = "이메일 전송")
    @PostMapping(value = "/email", consumes = "multipart/form-data")
    public SuccessResponse<Object> sendEmail(SendMailRequestDto sendMailRequestDto)throws MessagingException, IOException {
        sendAdsUseCase.sendEmail(sendMailRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "메신저 전송")
    @PostMapping(value="/messenger")
    public SuccessResponse<Object> sendMessenger(@RequestBody SendMsgRequestDto sendMsgRequestDto)
            throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        sendAdsUseCase.sendMsg(sendMsgRequestDto);
        return SuccessResponse.empty();
    }
}
