package record.everything.helper;

import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.Sort;

public class QuerydslHelper {

    private static final String SPACE_STRING = " ";
    private static final String EMPTY_STRING = "";
    private static final String SPACE_TO_EMPTY_STRING_TEMPLATE = "replace({0},' ','')";

    public static Predicate containsByStringPathAndStringValue(StringPath stringPath, String value) {
        String replaceString = value.replaceAll(SPACE_STRING, EMPTY_STRING);
        StringTemplate replaceStringColumn = Expressions.stringTemplate(SPACE_TO_EMPTY_STRING_TEMPLATE, stringPath);

        return replaceStringColumn.lower().contains(replaceString.toLowerCase());
    }

    public static BooleanExpression eqByPathAndObject(SimpleExpression<?> path, Object object) {
        Expression expression = ExpressionUtils.toExpression(object);
        return path.eq(expression);
    }

    public static BooleanExpression eqByPathAndPath(
            SimpleExpression<?> firstPath,
            Expression secondPath
    ) {
        return firstPath.eq(secondPath);
    }

    public static OrderSpecifier<?> getOrderSpecifier(Sort.Order order, NumberPath<?> numberPath) {
        return order.getDirection().isAscending() ? numberPath.asc() : numberPath.desc();
    }

    public static OrderSpecifier<?> getOrderSpecifier(Sort.Order order, Path<?> path, String fieldName) {
        Order direction = getDirection(order);
        return getSortedColumn(direction, path, fieldName);
    }

    private static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    private static Order getDirection(Sort.Order order) {
        return order.getDirection().isAscending() ? Order.ASC : Order.DESC;
    }
}
