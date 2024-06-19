package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> predicates = new HashMap<>();

    protected final void setPredicate(String key, Predicate<T> predicate) {
        predicates.put(key, predicate);
    }

    public final boolean isValid(T value) {
        for (Predicate<T> predicate : predicates.values()) {
            if (!predicate.test(value)) {
                return false;
            }
        }
        return true;
    }
}
