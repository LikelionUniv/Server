package likelion.univ.domain.user.entity;

import lombok.*;

import javax.persistence.*;

import static likelion.univ.domain.user.entity.AccountStatus.ACTIVE;
import static likelion.univ.domain.user.entity.Role.GUEST;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class AuthInfo {

    @Enumerated(EnumType.STRING)
    private LoginType loginType;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    // 01000000000 형태로
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static AuthInfo authInfoForSignUp(LoginType loginType, String email) {
        return AuthInfo.builder()
                .loginType(loginType)
                .email(email)
                .accountStatus(ACTIVE)
                .role(GUEST)
                .build();
    }
}
