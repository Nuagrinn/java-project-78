package helxlet.code.schema;

import java.util.Map;
import java.util.Objects;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        setPredicate("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        setPredicate("sizeof", value -> value != null && value.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        setPredicate(
                "shape",
                map -> schemas.entrySet().stream().allMatch(e -> {
                    var v = map.get(e.getKey());
                    var schema = e.getValue();
                    return schema.isValid((T) v);
                })
        );
        return this;
    }

}
