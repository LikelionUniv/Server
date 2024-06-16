package likelion.univ.domain.hackathon.entity;

import static likelion.univ.domain.user.entity.Role.GUEST;
import static likelion.univ.domain.user.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import likelion.univ.domain.hackathon.exception.NoAuthorityGuestApplyHackathon;
import likelion.univ.domain.hackathon.exception.NoAuthorityOrdinalApplyHackathon;
import likelion.univ.domain.university.entity.University;
import likelion.univ.domain.user.entity.AuthInfo;
import likelion.univ.domain.user.entity.UniversityInfo;
import likelion.univ.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayName("해커톤 신청서 (HackathonForm) 은(는)")
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class HackathonFormTest {

    @Test
    void GUEST_회원이_참여신청_시_예외() {
        // given
        User user = mock(User.class);
        AuthInfo geustAuthInfo = mock(AuthInfo.class);
        when(geustAuthInfo.getRole()).thenReturn(GUEST);
        when(user.getAuthInfo()).thenReturn(geustAuthInfo);
        HackathonForm hackathonForm = new HackathonForm(
                user,
                "name",
                "email",
                mock(University.class),
                "major",
                "01011112222",
                HackathonPart.PM,
                "team",
                false
        );

        // when & then
        assertThrows(
                NoAuthorityGuestApplyHackathon.class,
                hackathonForm::apply
        );
    }

    @Test
    void 기수가_12기_외_회원이_참여신청시_예외() {
        // given
        User user = mock(User.class);
        AuthInfo geustAuthInfo = mock(AuthInfo.class);
        when(geustAuthInfo.getRole()).thenReturn(USER);
        when(user.getUniversityInfo()).thenReturn(
                new UniversityInfo(mock(University.class), "major", 11L)
        );
        when(user.getAuthInfo()).thenReturn(geustAuthInfo);
        HackathonForm hackathonForm = new HackathonForm(
                user,
                "name",
                "email",
                mock(University.class),
                "major",
                "01011112222",
                HackathonPart.PM,
                "team",
                false
        );

        // when & then
        assertThrows(
                NoAuthorityOrdinalApplyHackathon.class,
                hackathonForm::apply
        );
    }

    @Test
    void 기수가_12기이며_GUEST_가_아닌_회원이_참여신청_가능() {
        // given
        User user = mock(User.class);
        AuthInfo geustAuthInfo = mock(AuthInfo.class);
        when(geustAuthInfo.getRole()).thenReturn(USER);
        when(user.getUniversityInfo()).thenReturn(
                new UniversityInfo(mock(University.class), "major", 12L)
        );
        when(user.getAuthInfo()).thenReturn(geustAuthInfo);
        HackathonForm hackathonForm = new HackathonForm(
                user,
                "name",
                "email",
                mock(University.class),
                "major",
                "01011112222",
                HackathonPart.PM,
                "team",
                false
        );

        // when & then
        assertDoesNotThrow(hackathonForm::apply);
    }
}
