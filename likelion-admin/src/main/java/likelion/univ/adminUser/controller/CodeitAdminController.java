package likelion.univ.adminUser.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.adminUser.dto.request.SendMsgRequestDto;
import likelion.univ.adminUser.usecase.SendEmailUseCase;
import likelion.univ.adminUser.usecase.SendNcpSmsUseCase;
import likelion.univ.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/codeit")
@Api(tags = {"코드잇"})
public class CodeitAdminController {

    private final SendEmailUseCase sendEmailUseCase;
    private final SendNcpSmsUseCase sendNcpSmsUseCase;


    @Operation(summary = "이메일 전송")
    @PostMapping(value = "/email", consumes = "multipart/form-data")
    public SuccessResponse<Object> sendEmail(SendMailRequestDto sendMailRequestDto)throws MessagingException, IOException {
        sendEmailUseCase.sendEmail(sendMailRequestDto);
        return SuccessResponse.empty();
    }

    @Operation(summary = "메신저 전송")
    @PostMapping(value="/messenger")
    public SuccessResponse<Object> sendMessenger(@RequestBody List<SendMsgRequestDto> sendMsgRequestDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException{
        sendNcpSmsUseCase.sendNcpSms(sendMsgRequestDto);
        return SuccessResponse.empty();
    }

}
