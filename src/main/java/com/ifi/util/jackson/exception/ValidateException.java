package com.ifi.util.jackson.exception;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;

public class ValidateException extends JsonParseException {

    public ValidateException(JsonParser p, String msg) {
        super(p, msg);
    }

    public ValidateException(JsonParser p, String msg, Throwable root) {
        super(p, msg, root);
    }

    public ValidateException(JsonParser p, String msg, JsonLocation loc) {
        super(p, msg, loc);
    }

    public ValidateException(JsonParser p, String msg, JsonLocation loc, Throwable root) {
        super(p, msg, loc, root);
    }
}
