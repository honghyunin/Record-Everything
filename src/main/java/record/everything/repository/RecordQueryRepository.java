package record.everything.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import record.everything.dto.record.RecordDto;
import record.everything.helper.ObjectHelper;
import record.everything.helper.QuerydslHelper;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static record.everything.domain.logging.QRecord.record;

@Repository
public class RecordQueryRepository extends BaseQueryRepository {


    public Page<RecordDto> search(String exercise, Integer count, String level, Pageable pageable) {

        BooleanBuilder booleanBuilder = makeRecordDtoSearchBooleanBuilder(exercise, count, level);

        List<RecordDto> recordDtos = queryFactory
                .select(getRecordDtoProjectionsConstructorExpression())
                .from(record)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(makeOrderSpecifiersByQEntityPathAndPageable(record, pageable))
                .fetch();

        JPAQuery<Long> recordCount = getRecordCountByBooleanBuilder(booleanBuilder);

        return PageableExecutionUtils.getPage(recordDtos, pageable, recordCount::fetchOne);
    }

    private JPAQuery<Long> getRecordCountByBooleanBuilder(BooleanBuilder booleanBuilder) {
        return queryFactory.select(count(record))
                .from(record)
                .where(booleanBuilder);
    }

    private ConstructorExpression<RecordDto> getRecordDtoProjectionsConstructorExpression() {
        return Projections.constructor(
                RecordDto.class,
                record.exercise,
                record.count,
                record.level,
                record.msg
        );
    }

    private BooleanBuilder makeRecordDtoSearchBooleanBuilder(String exercise, Integer count, String level) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (ObjectHelper.isNotEmpty(exercise)) {
            booleanBuilder.and(QuerydslHelper.containsByStringPathAndStringValue(record.exercise, exercise));
        }

        if (ObjectHelper.isNotEmpty(count)) {
            booleanBuilder.and(QuerydslHelper.eqByPathAndObject(record.count, count));
        }

        if (ObjectHelper.isNotEmpty(level)) {
            booleanBuilder.and(QuerydslHelper.containsByStringPathAndStringValue(record.level, level));
        }

        return booleanBuilder;
    }
}
