package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        setPredicate("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        setPredicate("minLength", value -> value != null && value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        setPredicate("contains", value -> value != null && value.contains(substring));
        return this;
    }
}
