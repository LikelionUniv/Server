package likelion.univ.auth.service;

import static likelion.univ.constant.StaticValue.REFRESH_TOKEN;

import likelion.univ.auth.dto.request.SignUpRequestDto;
import likelion.univ.auth.dto.response.AccountTokenDto;
import likelion.univ.auth.dto.response.AccountUserInfoDto;
import likelion.univ.auth.dto.response.IdTokenDto;
import likelion.univ.auth.processor.GenerateAccountTokenProcessor;
import likelion.univ.auth.processor.LoginByIdTokenProcessor;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.university.repository.UniversityRepository;
import likelion.univ.domain.user.entity.AuthInfo;
import likelion.univ.domain.user.entity.LoginType;
import likelion.univ.domain.user.entity.Profile;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import likelion.univ.domain.user.exception.EmailAlreadyRegistered;
import likelion.univ.domain.user.exception.NotSupportedLoginTypeException;
import likelion.univ.domain.user.repository.UserRepository;
import likelion.univ.domain.user.service.UserService;
import likelion.univ.exception.ExpiredTokenException;
import likelion.univ.exception.InvalidTokenException;
import likelion.univ.feign.oauth.google.RequestGoogleTokenClient;
import likelion.univ.feign.oauth.google.dto.GoogleTokenInfoDto;
import likelion.univ.feign.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.feign.oauth.kakao.dto.KakaoTokenInfoDto;
import likelion.univ.jwt.JwtProvider;
import likelion.univ.jwt.dto.DecodedJwtToken;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import likelion.univ.properties.GoogleProperties;
import likelion.univ.properties.KakaoProperties;
import likelion.univ.refreshtoken.exception.ExpiredRefreshTokenException;
import likelion.univ.refreshtoken.service.RefreshTokenRedisService;
import likelion.univ.utils.AuthenticatedUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClientAuthService {

    private final AuthenticatedUserUtils authenticatedUserUtils;
    private final UserRepository userRepository;
    private final LoginByIdTokenProcessor loginByIdTokenProcessor;
    private final UserService userService;
    private final GenerateAccountTokenProcessor generateAccountTokenProcessor;
    private final RefreshTokenRedisService refreshTokenRedisService;
    private final JwtProvider jwtProvider;
    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final RequestGoogleTokenClient requestGoogleTokenClient;
    private final KakaoProperties kakaoProperties;
    private final GoogleProperties googleProperties;
    private final UniversityRepository universityRepository;

    public AccountUserInfoDto getUserInfo() {
        Long currentUserId = authenticatedUserUtils.getCurrentUserId();
        User user = userRepository.getByIdWithUniversity(currentUserId);
        if (user.getUniversityInfo().getUniversity() == null) {
            return AccountUserInfoDto.of(user, null);
        }
        return AccountUserInfoDto.of(user, user.getUniversityInfo().getUniversity().getName());
    }

    public AccountTokenDto login(String loginType, String idToken) {
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userRepository.existsByAuthInfoEmail(userInfo.getEmail())) {
            return AccountTokenDto.notRegistered();
        }
        User user = userService.login(LoginType.fromValue(loginType), userInfo.getEmail());
        return generateAccountTokenProcessor.createToken(user);
    }

    public AccountTokenDto refreshToken(String refreshToken) {
        DecodedJwtToken decodedJwtToken = decodeRefreshToken(refreshToken);
        User user = userRepository.getById(decodedJwtToken.getUserId());
        if (refreshTokenRedisService.checkToken(user.getId(), refreshToken)) {
            return generateAccountTokenProcessor.refreshToken(user, refreshToken);
        }
        throw new InvalidTokenException();
    }

    private DecodedJwtToken decodeRefreshToken(String refreshToken) {
        try {
            return jwtProvider.decodeToken(refreshToken, REFRESH_TOKEN);
        } catch (ExpiredTokenException e) {
            throw new ExpiredRefreshTokenException();
        }
    }

    public IdTokenDto requestIdToken(String loginType, String code) {
        switch (loginType) {
            case "kakao":
                KakaoTokenInfoDto kakaoTokenInfoDto = requestKakaoTokenClient.getToken(
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl(),
                        code,
                        kakaoProperties.getClientSecret());
                return IdTokenDto.of(kakaoTokenInfoDto.getIdToken());
            case "google":
                GoogleTokenInfoDto googleTokenInfoDto = requestGoogleTokenClient.getToken(
                        googleProperties.getClientId(),
                        googleProperties.getClientSecret(),
                        code,
                        googleProperties.getRedirectUrl());
                return IdTokenDto.of(googleTokenInfoDto.getIdToken());
        }
        throw new NotSupportedLoginTypeException();
    }

    @Transactional
    public AccountTokenDto signup(
            String loginType,
            String idToken,
            SignUpRequestDto signUpRequestDto
    ) {
        UserInfoFromIdToken userInfo = loginByIdTokenProcessor.execute(loginType, idToken);
        if (!userRepository.existsByAuthInfoEmail(userInfo.getEmail())) {
            Profile profile = Profile.fromName(signUpRequestDto.getName());
            University university = universityRepository.getByName(signUpRequestDto.getUniversityName());
            UniversityInfo universityInfo = UniversityInfo.universityInfoForSignUp(
                    university,
                    signUpRequestDto.getMajor()
            );
            AuthInfo authInfo = AuthInfo.authInfoForSignUp((LoginType.fromValue(loginType)), userInfo.getEmail());
            User user = userService.signUp(profile, authInfo, universityInfo);
            return generateAccountTokenProcessor.createToken(user);
        }
        throw new EmailAlreadyRegistered();
    }
}
