package io.hexlet;


import helxlet.code.Validator;
import helxlet.code.schema.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));

        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("hexlet"));

        schema = v.string();
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));
    }
}
