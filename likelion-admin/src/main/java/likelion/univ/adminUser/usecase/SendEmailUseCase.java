package likelion.univ.adminUser.usecase;

import likelion.univ.adminUser.dto.request.SendMailRequestDto;
import likelion.univ.annotation.UseCase;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.User;
import likelion.univ.mail.SendMail;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class SendEmailUseCase {

    private final SendMail sendMail;
    private final UserAdaptor userAdaptor;

    @SneakyThrows
    public void excute(SendMailRequestDto sendMailRequestDto) throws IOException {

        List<User> users = userAdaptor.findAllUser();
        List<String> toAddress = new ArrayList<>();
        for(User user : users) {
            toAddress.add(user.getAuthInfo().getEmail());
        }

        sendMail.sendMail(sendMailRequestDto.getTitle(), sendMailRequestDto.getBody(),
                sendMailRequestDto.getFiles(), toAddress.toArray(new String[users.size()]));
    }


}
