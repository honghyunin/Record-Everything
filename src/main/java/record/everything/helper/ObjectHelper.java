package record.everything.helper;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class ObjectHelper {

    public static boolean isEmpty(Object object) {
        // TODO 원시타입을 Object 타입으로 변환했을 때 원시타입인지 체킹 가능한지 확인 필요 (임시로 Javadoc 경고 작성)

        if (object == null) {
            return true;
        }

        if (object instanceof CharSequence) {
            return ((CharSequence) object).length() == 0;
        }

        if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        }

        if (object instanceof Collection<?>) {
            return ((Collection<?>) object).isEmpty();
        }

        if (object instanceof Map<?, ?>) {
            return ((Map<?, ?>) object).isEmpty();
        }

        if(object instanceof Optional) {
            return  ((Optional<?>) object).isEmpty();
        }

        return false;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}
