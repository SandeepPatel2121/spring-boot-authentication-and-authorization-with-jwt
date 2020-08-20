package com.security.demo.util;

import org.springframework.http.HttpStatus;

/**
 *
 * @author bypt-dev-laptop-9
 */
public class ResponseStatus {
    
     public static ResponseModel create(String message, Object t, HttpStatus httpStatus, int httpStatusCode) {
        ResponseModel rs = new ResponseModel();
        rs.setMessage(message);
        rs.setData(t);
        rs.setStatus(httpStatus);
        rs.setStatusCode(httpStatusCode);
        return rs;
    }

    
}
