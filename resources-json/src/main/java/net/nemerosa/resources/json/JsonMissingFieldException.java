package net.nemerosa.resources.json;

public class JsonMissingFieldException extends RuntimeException {
    public JsonMissingFieldException(String field) {
        super("Required field missing: " + field);
    }
}
