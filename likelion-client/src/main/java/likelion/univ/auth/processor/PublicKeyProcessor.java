package likelion.univ.auth.processor;

import static likelion.univ.common.constant.RedisKey.GOOGLE_PUBLIC_KEYS;
import static likelion.univ.common.constant.RedisKey.KAKAO_PUBLIC_KEYS;

import java.security.interfaces.RSAPublicKey;
import likelion.univ.annotation.Processor;
import likelion.univ.feign.oauth.google.RequestGooglePublicKeysClient;
import likelion.univ.feign.oauth.kakao.RequestKakaoTokenClient;
import likelion.univ.feign.oauth.oidc.PublicKeyDto;
import likelion.univ.feign.oauth.oidc.PublicKeysDto;
import likelion.univ.utils.PublicKeyGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

@Processor
@RequiredArgsConstructor
public class PublicKeyProcessor {

    private final RequestKakaoTokenClient requestKakaoTokenClient;
    private final RequestGooglePublicKeysClient requestGooglePublicKeysClient;

    @Cacheable(value = KAKAO_PUBLIC_KEYS, cacheManager = "redisCacheManager")
    public PublicKeysDto getCachedKakaoPublicKeys() {
        return requestKakaoTokenClient.getPublicKeys();
    }

    @Cacheable(value = GOOGLE_PUBLIC_KEYS, cacheManager = "redisCacheManager")
    public PublicKeysDto getCachedGooglePublicKeys() {
        return requestGooglePublicKeysClient.getPublicKeys();
    }

    public RSAPublicKey generatePublicKey(PublicKeyDto key) {
        return PublicKeyGenerator.execute(key.getKty(), key.getN(), key.getE());
    }
}
