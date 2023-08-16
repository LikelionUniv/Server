package likelion.univ.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import likelion.univ.exception.ExpiredTokenException;
import likelion.univ.exception.InvalidSignatureTokenException;
import likelion.univ.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    public Jws<Claims> getClaim(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new InvalidSignatureTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e){
            throw new InvalidTokenException();
        }
    }

    private String publishToken(Long uid, String role, String type){
        Date now = new Date();
        return Jwts.builder()
                .setIssuer("LikelionUniv")
                .setSubject(uid.toString())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getAccessTokenExp()))
                .claim("type",type)
                .claim("role", role)
                .signWith(getSecretKey())
                .compact();
    }

    public String generateAccessToken(Long uid, String role){
        return publishToken(uid,role,"AccessToken");
    }
    public String generateRefreshToken(Long uid, String role){
        return publishToken(uid,role,"RefreshToken");
    }

    public Long getUserId(String token){
        Claims claims = getClaim(token).getBody();
        return Long.valueOf(claims.getSubject());
    }
}