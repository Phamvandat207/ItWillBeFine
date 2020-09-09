package com.ifi.util.message;

import lombok.Value;

@Value
public class ResponseMessage {
    int status;
    String message;
}
