package com.security.demo.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bypt-dev-laptop-9
 */
@Setter
@Getter
public class UserModel {

    private Long id;
    private String username;
    private String password;
    private String sessionToken;
    private Integer age;
    private Long salary;

}
