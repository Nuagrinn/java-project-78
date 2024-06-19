package helxlet.code;

import helxlet.code.schema.MapSchema;
import helxlet.code.schema.NumberSchema;
import helxlet.code.schema.StringSchema;

public class Validator {

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
