package com.security.demo.auth.controller;

import com.google.common.base.Strings;
import com.security.demo.entity.User;
import com.security.demo.model.UserModel;
import com.security.demo.jwt.config.JwtUtil;
import com.security.demo.security.config.ApplicationUserDetail;
import com.security.demo.security.config.UserDetailService;
import com.security.demo.util.HttpResponseStatus;
import com.security.demo.util.ResponseModel;
import com.security.demo.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bypt-dev-laptop-9
 */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity<ResponseModel> createAuthenticationToken(@RequestBody UserModel userModel) throws Exception {
        User user;
        ResponseModel rs = null;
        try {
            final Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userModel.getUsername(),
                            userModel.getPassword()
                    )
            );
            ApplicationUserDetail userDetails = (ApplicationUserDetail) userDetailService.loadUserByUsername(userModel.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtil.generateToken(authentication);
            if (!Strings.isNullOrEmpty(token) && userDetails != null) {
                rs = ResponseStatus.create(HttpResponseStatus.LOGIN_SUCCESS,
                        convertUser(userDetails.getUser(), token),
                        HttpStatus.OK, HttpStatus.OK.value());
            }
        } catch (BadCredentialsException ex) {
            rs = ResponseStatus.create(HttpResponseStatus.LOGIN_ERROR,
                    null,
                    HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
        } catch (Exception e) {
            rs = ResponseStatus.create(HttpResponseStatus.LOGIN_ERROR,
                    null,
                    HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
        }
        return new ResponseEntity<>(rs, HttpStatus.OK);
    }

    private UserModel convertUser(User user, String token) {
        UserModel uModel = new UserModel();
        
        uModel.setAge(user.getAge());
        uModel.setSalary(user.getSalary());
        uModel.setId(user.getId());
        uModel.setUsername(user.getUsername());
        uModel.setSessionToken(token);
        
        return uModel;
    }

}
