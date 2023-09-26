package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.NcpSmsRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.feign.ncpSms.SendNcpSms;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SendNcpSmsUseCase {
    private final SendNcpSms sendNcpSms;
    private final UserAdaptor userAdaptor;

    public void excute(NcpSmsRequestDto ncpSmsRequestDto)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        List<User> users = userAdaptor.findAllUser();
        List<String> toPhoneNums = new ArrayList<>();
        for(User user : users){
            toPhoneNums.add(user.getProfile().getPhoneNumber() );
        }
        sendNcpSms.sendNcpSms(ncpSmsRequestDto.getContent(),toPhoneNums);

    }
}
