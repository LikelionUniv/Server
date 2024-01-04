package likelion.univ.common.query;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.querydsl.core.types.Order.ASC;

public interface BaseQueryDslRepository {
    default OrderSpecifier[] getOrdersBySort(EntityPathBase<?> qEntity, Sort sort) {
        return sort.stream()
                .map(order -> createOrderSpecifier(qEntity, order))
                .collect(Collectors.toList()).toArray(new OrderSpecifier[0]);
    }
    private OrderSpecifier<?> createOrderSpecifier(EntityPathBase<?> qEntity, Sort.Order order){
        PathBuilder pathBuilder = new PathBuilder(qEntity.getClass(), qEntity.getMetadata().getName());
        return new OrderSpecifier<>(toQueryDslOrder(order), pathBuilder.get(order.getProperty()));
    }
    private boolean isFieldExists(EntityPathBase<?> qEntity, String fieldName) {
        Field[] fields = qEntity.getClass().getDeclaredFields();
        return Arrays.stream(fields).anyMatch(field -> field.getName().equals(fieldName));
    }
    private Order toQueryDslOrder(Sort.Order order) {
        return order.isAscending() ? ASC : Order.DESC;
    }
}
