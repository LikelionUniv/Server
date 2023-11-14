package likelion.univ.common.constant;

public class RedisKey {
    public static final int DEFAULT_EXPIRE_DAY = 1;

    public static final String KAKAO_PUBLIC_KEYS = "kakaoPublicKeys";
    public static final String GOOGLE_PUBLIC_KEYS = "googlePublicKeys";

    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String POST_COUNT_INFO = "postCount";
    public static final String USER_FOLLOW_NUM = "userFollowNum";
    public static final Long REFRESH_TOKEN_EXPIRE_SEC = 1209600L;
    public static final Long POST_COUNT_INFO_EXPIRE_SEC = 86400L;
    public static final Long USER_FOLLOW_NUM_EXPIRE_SEC = 86400L;
}
