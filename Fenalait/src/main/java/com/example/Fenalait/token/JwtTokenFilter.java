package com.example.Fenalait.token;



import java.io.IOException;

 

import javax.servlet.FilterChain;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.util.ObjectUtils;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Fenalait.model.Role;
import com.example.Fenalait.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 

                HttpServletResponse response, FilterChain filterChain)

            throws ServletException, IOException {

 

        if (!hasAuthorizationBearer(request)) {

            filterChain.doFilter(request, response);

            return;

        }

        String tokenAccess = getAccessToken(request);

        if (!jwtUtil.validateAccessToken(tokenAccess)) {

            filterChain.doFilter(request, response);

            return;

        }
        String tokenRefresh = getRefreshToken(request);

        if (!jwtUtil.validateRefreshToken(tokenRefresh)) {

            filterChain.doFilter(request, response);

            return;

        }
        
        setAuthenticationContext(tokenAccess , tokenRefresh, request);

        filterChain.doFilter(request, response);

    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {

        String header = request.getHeader("Authorization");
        System.out.println("Authorisation header : " + header);
        
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {

            return false;

        }
        return true;

    }
    
    private String getAccessToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        String tokenAccess = header.split(" ")[1].trim();

        return tokenAccess;

    }
    private String getRefreshToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        String tokenRefresh = header.split(" ")[1].trim();

        return tokenRefresh;

    }

 
    private void setAuthenticationContext(String tokenAccess, String tokenRefresh, HttpServletRequest request) {

        UserDetails userDetails = getUserDetails(tokenAccess, tokenRefresh);

        UsernamePasswordAuthenticationToken 

            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(

                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private UserDetails getUserDetails(String accessToken, String tokenRefresh) {
        User userDetails = new User();
        Claims claims = jwtUtil.parseClaims(accessToken, tokenRefresh);
        
        String claimRoles = (String) claims.get("roles");
        
        System.out.println("claimRoles: " + claimRoles);
        claimRoles = claimRoles.replace("[", "").replace("]", "");
        String[] rolesNames = claimRoles.split(",");
        
        for(String arolesNames : rolesNames) {
        	userDetails.addRole(new Role(arolesNames));
        }
        
        String subject = (String) claims.get(Claims.SUBJECT);                   
        String[] jwtSubject = subject.split(",");


        userDetails.setId(Long.parseLong(jwtSubject[0]));
        userDetails.setEmail(jwtSubject[1]);

        return userDetails;

    }

    
} 