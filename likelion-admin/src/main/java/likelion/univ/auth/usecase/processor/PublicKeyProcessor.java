package likelion.univ.auth.usecase.processor;

import likelion.univ.annotation.Processor;
import likelion.univ.api.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.api.oauth.oidc.PublicKeyDto;
import likelion.univ.api.oauth.oidc.PublicKeysDto;
import likelion.univ.common.constant.RedisKey;
import likelion.univ.utils.PublicKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.security.interfaces.RSAPublicKey;

@Processor
@RequiredArgsConstructor
public class PublicKeyProcessor {
    private final RequestKakaoTokenClient requestKakaoTokenClient;

    @Cacheable(value = RedisKey.KAKAO_PUBLIC_KEYS, cacheManager = "redisCacheManager")
    public PublicKeysDto getCachedKakaoPublicKeys(){
        return requestKakaoTokenClient.getPublicKeys();
    }


    public RSAPublicKey generatePublicKey(PublicKeyDto key){
        return PublicKeyGenerator.excute(key.getKty(), key.getN(), key.getE());
    }
}
