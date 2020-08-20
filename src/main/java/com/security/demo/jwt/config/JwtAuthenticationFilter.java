package com.security.demo.jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.Strings;
import com.security.demo.util.ApplicationConstants;
import com.security.demo.util.HttpResponseStatus;
import com.security.demo.util.ResponseModel;
import com.security.demo.util.ResponseStatus;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author bypt-dev-laptop-9
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(ApplicationConstants.HEADER_STRING);
        String json = "";
        ResponseModel rs = null;
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(ApplicationConstants.TOKEN_PREFIX)) {
            authToken = header.replace(ApplicationConstants.TOKEN_PREFIX, "");
            try {
                username = jwtUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException | ExpiredJwtException | SignatureException e) {

                log.info("CAME HERE : " + e.getMessage());

                rs = ResponseStatus.create(e.getMessage(),
                        null,
                        HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
                res.setContentType("application/json");
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                json = ow.writeValueAsString(rs);
            }
        } else {
            rs = ResponseStatus.create(HttpResponseStatus.ACCESS_TOKEN_MISSING,
                    null,
                    HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value());
            res.setContentType("application/json");
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(rs);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtUtil.getAuthentication(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        if (!Strings.isNullOrEmpty(json)) {
            res.getWriter().write(json);
            res.setStatus(200);
        } else {
            chain.doFilter(req, res);
        }
    }

}
