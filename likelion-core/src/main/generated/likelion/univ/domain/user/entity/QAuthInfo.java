package likelion.univ.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthInfo is a Querydsl query type for AuthInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAuthInfo extends BeanPath<AuthInfo> {

    private static final long serialVersionUID = 427043067L;

    public static final QAuthInfo authInfo = new QAuthInfo("authInfo");

    public final EnumPath<LoginType> loginType = createEnum("loginType", LoginType.class);

    public final StringPath oid = createString("oid");

    public QAuthInfo(String variable) {
        super(AuthInfo.class, forVariable(variable));
    }

    public QAuthInfo(Path<? extends AuthInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthInfo(PathMetadata metadata) {
        super(AuthInfo.class, metadata);
    }

}

