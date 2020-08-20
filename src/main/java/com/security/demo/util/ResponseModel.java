package com.security.demo.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 *
 * @author bypt-dev-laptop-9
 */
@Getter
@Setter
@ToString
public class ResponseModel {
    
    private HttpStatus status;
    private int statusCode;
    private String message;
    private Object data;
    
}
