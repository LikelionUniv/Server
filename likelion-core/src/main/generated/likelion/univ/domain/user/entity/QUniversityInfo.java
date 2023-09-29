package likelion.univ.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUniversityInfo is a Querydsl query type for UniversityInfo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUniversityInfo extends BeanPath<UniversityInfo> {

    private static final long serialVersionUID = -671966431L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUniversityInfo universityInfo = new QUniversityInfo("universityInfo");

    public final StringPath major = createString("major");

    public final NumberPath<Long> ordinal = createNumber("ordinal", Long.class);

    public final likelion.univ.domain.university.entity.QUniversity university;

    public QUniversityInfo(String variable) {
        this(UniversityInfo.class, forVariable(variable), INITS);
    }

    public QUniversityInfo(Path<? extends UniversityInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUniversityInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUniversityInfo(PathMetadata metadata, PathInits inits) {
        this(UniversityInfo.class, metadata, inits);
    }

    public QUniversityInfo(Class<? extends UniversityInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.university = inits.isInitialized("university") ? new likelion.univ.domain.university.entity.QUniversity(forProperty("university")) : null;
    }

}

