package likelion.univ.refreshtoken.service;

import likelion.univ.refreshtoken.dao.RefreshTokenRedisDao;
import likelion.univ.refreshtoken.entity.RefreshToken;
import likelion.univ.refreshtoken.exception.ExpiredRefreshTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {
    private final RefreshTokenRedisDao refreshTokenRedisDao;

    public void save(Long id, String token){
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(id)
                .token(token)
                .build();
        refreshTokenRedisDao.save(id, refreshToken);
    }
    public Boolean checkToken(Long id, String token){
        RefreshToken refreshToken = refreshTokenRedisDao.findById(id)
                .orElseThrow(() -> new ExpiredRefreshTokenException());
        if(token.equals(refreshToken.getToken())) return true;
        else return false;
    }
}
