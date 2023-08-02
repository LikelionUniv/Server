package likelion.univ.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExample is a Querydsl query type for Example
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExample extends EntityPathBase<Example> {

    private static final long serialVersionUID = 778552540L;

    public static final QExample example = new QExample("example");

    public final likelion.univ.domain.entity.common.QBaseTimeEntity _super = new likelion.univ.domain.entity.common.QBaseTimeEntity(this);

    public final StringPath column = createString("column");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QExample(String variable) {
        super(Example.class, forVariable(variable));
    }

    public QExample(Path<? extends Example> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExample(PathMetadata metadata) {
        super(Example.class, metadata);
    }

}

