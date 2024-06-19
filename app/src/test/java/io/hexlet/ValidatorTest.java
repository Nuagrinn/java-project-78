package io.hexlet;

import helxlet.code.Validator;
import helxlet.code.schema.BaseSchema;
import helxlet.code.schema.MapSchema;
import helxlet.code.schema.NumberSchema;
import helxlet.code.schema.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));

        schema.minLength(5);

        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));

        schema.contains("ex");

        assertFalse(schema.isValid("hex"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));

        schema.positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));

        // Additional tests
        schema.sizeof(1);
        assertFalse(schema.isValid(data));

        schema.sizeof(2);
        assertTrue(schema.isValid(data));

        data.put("key3", "value3");
        assertFalse(schema.isValid(data));
    }

    @Test
    public void testMapSchemaShape() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("Jo"));
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
