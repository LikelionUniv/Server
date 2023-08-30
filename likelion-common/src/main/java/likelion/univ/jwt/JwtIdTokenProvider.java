package likelion.univ.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import likelion.univ.exception.ExpiredTokenException;
import likelion.univ.exception.IncorrectIssuerTokenException;
import likelion.univ.exception.InvalidSignatureTokenException;
import likelion.univ.exception.InvalidTokenException;
import likelion.univ.jwt.dto.UserInfoFromIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtIdTokenProvider {
    /* kid 서명검증없이 가져오기  */
    public String getKid(String idToken){
        try{
            String[] idTokenParts = idToken.split("\\.");
            String encodedHeader = idTokenParts[0];
            String decodedHeader = new String(Base64.getUrlDecoder().decode(encodedHeader), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(decodedHeader, java.util.Map.class);
            return map.get("kid");
        } catch (Exception e){
            throw new InvalidTokenException();
        }
    }

    /* iss, aud, 만료시간 검증 & 서명검증 & 유저정보 가져오기 */
    public UserInfoFromIdToken getUserInfo(String idToken, RSAPublicKey publicKey, String iss, String aud) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .requireIssuer(iss)
                    .requireAudience(aud)
                    .build()
                    .parseClaimsJws(idToken)
                    .getBody();
            return UserInfoFromIdToken.builder()
                    .profileImage(claims.get("profileImage", String.class))
                    .email(claims.get("email", String.class))
                    .build();

        } catch (SignatureException exception) {
            throw new InvalidSignatureTokenException();
        }catch (IncorrectClaimException exception){
            throw new IncorrectIssuerTokenException();
        }catch (ExpiredJwtException exception) {
            throw new ExpiredTokenException();
        } catch (Exception exception){
            throw new InvalidTokenException();
        }
    }



}