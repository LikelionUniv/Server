package likelion.univ.auth.usecase;

import likelion.univ.annotation.UseCase;
import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.processor.GenerateAccountTokenProcessor;
import likelion.univ.auth.processor.LoginByIdTokenProcessor;
import likelion.univ.domain.university.adaptor.UniversityAdaptor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.adaptor.UserAdaptor;
import likelion.univ.domain.user.entity.*;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.service.UserDomainService;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class SignUpUseCase {
    private final UniversityAdaptor universityAdaptor;
    private final UserDomainService userDomainService;
    private final UserAdaptor userAdaptor;
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;

    public AccountTokenDto execute(String loginType,
                                   String idToken,
                                   SignUpRequestDto signUpRequestDto){
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userAdaptor.checkEmail(userInfo.getEmail())){
            Profile profile = Profile.fromName(signUpRequestDto.getName());

            University university = universityAdaptor.findByName(signUpRequestDto.getUniversityName());
            UniversityInfo universityInfo = UniversityInfo.universityInfoForSignUp(university,
                    signUpRequestDto.getMajor());

            AuthInfo authInfo = AuthInfo.authInfoForSignUp((LoginType.fromValue(loginType)), userInfo.getEmail());
            User user = userDomainService.signUp(profile, authInfo, universityInfo);
            return generateAccountTokenProcessor.createToken(user);
        }else throw new EmailAlreadyRegistered();
    }
}
