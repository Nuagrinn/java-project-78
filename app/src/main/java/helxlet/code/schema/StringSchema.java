package helxlet.code.schema;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {
    private boolean required;
    private int minLength;
    private String contains;
    private List<String> errors;

    public StringSchema() {
        this.required = false;
        this.minLength = 0;
        this.contains = null;
        this.errors = new ArrayList<>();
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.contains = substring;
        return this;
    }

    public boolean isValid(String value) {
        this.errors.clear();

        if (value == null || value.isEmpty()) {
            if (this.required) {
                this.errors.add("Value is required");
                return false;
            } else {
                return true;
            }
        }

        if (this.required && (value == null || value.isEmpty())) {
            this.errors.add("Value is required");
            return false;
        }

        if (value.length() < this.minLength) {
            this.errors.add("Value is shorter than min length");
            return false;
        }

        if (this.contains != null && !value.contains(this.contains)) {
            this.errors.add("Value does not contain the required substring");
            return false;
        }

        return true;
    }
}
