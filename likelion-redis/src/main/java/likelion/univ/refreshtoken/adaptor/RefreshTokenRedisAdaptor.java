package likelion.univ.refreshtoken.adaptor;

import likelion.univ.annotation.Adaptor;
import likelion.univ.refreshtoken.dao.RefreshTokenRedisDao;
import likelion.univ.refreshtoken.entity.RefreshToken;
import likelion.univ.refreshtoken.exception.ExpiredRefreshTokenException;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RefreshTokenRedisAdaptor {
    private final RefreshTokenRedisDao refreshTokenRedisDao;

    public void save(Long id, RefreshToken refreshToken){
        refreshTokenRedisDao.save(id, refreshToken);
    }
    public RefreshToken findById(Long id){
        return refreshTokenRedisDao.findById(id)
                .orElseThrow(() -> new ExpiredRefreshTokenException());
    }
}
