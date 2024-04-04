package likelion.univ.common.query;

import static com.querydsl.core.types.Order.ASC;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

public interface BaseQueryDslRepository {

    default OrderSpecifier[] getOrdersBySort(EntityPathBase<?> qEntity, Sort sort) {
        return sort.stream()
                .map(order -> createOrderSpecifier(qEntity, order))
                .collect(Collectors.toList()).toArray(new OrderSpecifier[0]);
    }

    private OrderSpecifier<?> createOrderSpecifier(EntityPathBase<?> qEntity, Sort.Order order) {
        PathBuilder pathBuilder = new PathBuilder(qEntity.getClass(), qEntity.getMetadata().getName());
        Path<?> propertyPath = pathBuilder.get(order.getProperty());
        if (isCollectionType(propertyPath)) {
            // 속성이 컬렉션인 경우, size() 메서드를 사용하여 크기를 얻음
            return new OrderSpecifier<>(toQueryDslOrder(order),
                    Expressions.numberTemplate(Integer.class, "{0}.size()", propertyPath));
        } else {
            // 컬렉션이 아닌 경우 기존의 경로를 사용
            return new OrderSpecifier<>(toQueryDslOrder(order), pathBuilder.get(order.getProperty()));
        }
    }

    private boolean isFieldExists(EntityPathBase<?> qEntity, String fieldName) {
        Field[] fields = qEntity.getClass().getDeclaredFields();
        return Arrays.stream(fields).anyMatch(field -> field.getName().equals(fieldName));
    }

    private boolean isCollectionType(Path<?> path) {
        Class<?> type = path.getType();
        // 여기에서 컬렉션 또는 맵인지 확인합니다.
        return Iterable.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type);
    }

    private Order toQueryDslOrder(Sort.Order order) {
        return order.isAscending() ? ASC : Order.DESC;
    }
}
