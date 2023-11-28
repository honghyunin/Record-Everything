package record.everything.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import record.everything.exception.ErrorCode;
import record.everything.exception.EverythingException;
import record.everything.helper.ObjectHelper;
import record.everything.helper.QuerydslHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseQueryRepository {

    @Autowired protected JPAQueryFactory queryFactory;

    private static final String EXERCISE = "exercise";
    private static final String COUNT = "count";
    private static final String LEVEL = "level";

    protected OrderSpecifier<?>[] makeOrderSpecifiersByQEntityPathAndPageable(Path<?> path, Pageable pageable) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        if (ObjectHelper.isNotEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                switch (order.getProperty()) {
                    case EXERCISE:
                        orderSpecifiers.add(QuerydslHelper.getOrderSpecifier(order, path, EXERCISE));
                        break;
                    case COUNT:
                        orderSpecifiers.add(QuerydslHelper.getOrderSpecifier(order, path, COUNT));
                        break;
                    case LEVEL:
                        orderSpecifiers.add(QuerydslHelper.getOrderSpecifier(order, path, LEVEL));
                        break;
                    default:
                        throw new EverythingException(ErrorCode.BAD_REQUEST);
                }
            }
        }

        return orderSpecifiers.toArray(OrderSpecifier<?>[]::new);
    }
}
