package likelion.univ.domain.follow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * Qfollow is a Querydsl query type for follow
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class Qfollow extends EntityPathBase<follow> {

    private static final long serialVersionUID = 1429579132L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final Qfollow follow = new Qfollow("follow");

    public final likelion.univ.domain.common.QBaseTimeEntity _super = new likelion.univ.domain.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final likelion.univ.domain.user.entity.QUser followee;

    public final likelion.univ.domain.user.entity.QUser follower;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public Qfollow(String variable) {
        this(follow.class, forVariable(variable), INITS);
    }

    public Qfollow(Path<? extends follow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public Qfollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public Qfollow(PathMetadata metadata, PathInits inits) {
        this(follow.class, metadata, inits);
    }

    public Qfollow(Class<? extends follow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.followee = inits.isInitialized("followee") ? new likelion.univ.domain.user.entity.QUser(forProperty("followee"), inits.get("followee")) : null;
        this.follower = inits.isInitialized("follower") ? new likelion.univ.domain.user.entity.QUser(forProperty("follower"), inits.get("follower")) : null;
    }

}

