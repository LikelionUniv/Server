package likelion.univ.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;

//    public Key getSecretKey() {
//        return Keys.hmacShaKeyFor(secretKey.getBytes());
//    }
//
//
//    public String generateRefreshToken(Long uid, String role){
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam("typ", "REFRESH_TOKEN")
//                .setHeaderParam("alg", "HS256")
//                .setIssuer("DeepLook")
//                .setSubject(uid.toString())
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRE_TIME))
//                .claim("role", role)
//                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String generateAccessToken(Long uid,String role){
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam("typ", "ACCESS_TOKEN")
//                .setHeaderParam("alg", "HS256")
//                .setSubject(uid.toString())
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
//                .claim("role", role)
//                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//    public Long findUserIdByJwt(String token){
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(getSecretKey()).build()
//                    .parseClaimsJws(token)
//                    .getBody();
//            Long userId = Long.valueOf(claims.getSubject());
//
//            return userId;
//        } catch (ExpiredJwtException e) {
//            return Long.valueOf(0);
//        }
//    }
//
//
//    public boolean validateToken(String token){
//        try {
//            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
//            return true;
////        } catch (SignatureException ex) {
////            log.error("Invalid JWT signature");
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        } catch (NullPointerException ex){
//            log.error("JWT RefreshToken is empty");
//        }
//        return false;
//    }
//
//    /**
//     Jwt Token에 담긴 유저 정보를 DB에 검색하고, 해당 유저의 권한처리를 위해 Context에 담는 Authentication 객체를 반환
//     */
//    public Authentication getAuthentication(String token){
//        LoginUser loginUser =customUserDetailService.loadUserByUsername(String.valueOf(this.findUserIdByJwt(token)));
//        return new UsernamePasswordAuthenticationToken(loginUser, "", loginUser.getAuthorities());
//    }

}