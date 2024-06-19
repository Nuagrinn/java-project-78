package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        setPredicate("requeired", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        setPredicate("positive", value -> value == null || value.doubleValue() > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        setPredicate("range", value -> value != null && value.doubleValue() >= min && value.doubleValue() <= max);
        return this;
    }
}
