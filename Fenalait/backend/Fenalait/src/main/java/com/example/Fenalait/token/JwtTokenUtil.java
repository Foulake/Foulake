package com.example.Fenalait.token;

import java.util.Date;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Fenalait.model.User;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import io.jsonwebtoken.UnsupportedJwtException;

 

@Component

public class JwtTokenUtil {

	 private static final long EXPIRE_DURATION_1 = 24 * 60 * 1000; // 24h
	 
	 private static final long EXPIRE_DURATION_2 = 48 * 60 * 60 * 1000; // 24h

	 @Value(value = "${app.jwt.secret}")
	 private  String SECRET_KEY;
	 
	 public String generateAccessToken(User user) {
		 return Jwts.builder()
				 .setSubject(user.getId() + "," +user.getEmail())
				 .claim("roles", user.getRoles().toString())
				 .setIssuer("code Semaf")
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION_1))
				 .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				 .compact();
	 }
	
	 public String generateRefreshToken(User user) {
		 return Jwts.builder()
				 .setSubject(user.getId() + "," +user.getEmail())
				 .claim("roles", user.getRoles().toString())
				 .setIssuer("code Semaf")
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION_2))
				 .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				 .compact();
	 }
    // previous code is not shown... 

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public boolean validateAccessToken(String tokenAccess) {

        try {

            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(tokenAccess);

            return true;

        } catch (ExpiredJwtException ex) {

            LOGGER.error("JWT expired", ex.getMessage());

        } catch (IllegalArgumentException ex) {

            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());

        } catch (MalformedJwtException ex) {

            LOGGER.error("JWT is invalid", ex);

        } catch (UnsupportedJwtException ex) {

            LOGGER.error("JWT is not supported", ex);

        } catch (SignatureException ex) {

            LOGGER.error("Signature validation failed");

        }

         

        return false;

    }

    public boolean validateRefreshToken(String tokenRefresh) {

        try {

            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(tokenRefresh);

            return true;

        } catch (ExpiredJwtException ex) {

            LOGGER.error("JWT expired", ex.getMessage());

        } catch (IllegalArgumentException ex) {

            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());

        } catch (MalformedJwtException ex) {

            LOGGER.error("JWT is invalid", ex);

        } catch (UnsupportedJwtException ex) {

            LOGGER.error("JWT is not supported", ex);

        } catch (SignatureException ex) {

            LOGGER.error("Signature validation failed");

        }

         

        return false;

    }

     

    public String getSubject(String tokenAccess, String tokenRefresh) {

        return parseClaims(tokenAccess, tokenRefresh).getSubject();

    }

    
	public Claims parseClaims(String tokenAccess, String tokenRefresh) {
		
		return Jwts.parser()

                .setSigningKey(SECRET_KEY)

                .parseClaimsJws(tokenAccess)

                .getBody();
	}

	
} 