package likelion.univ.refreshtoken.service;

import likelion.univ.refreshtoken.adaptor.RefreshTokenRedisAdaptor;
import likelion.univ.refreshtoken.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {
    private final RefreshTokenRedisAdaptor refreshTokenRedisAdaptor;

    public void save(Long id, String token){
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(id)
                .token(token)
                .build();
        refreshTokenRedisAdaptor.save(id, refreshToken);
    }
}
