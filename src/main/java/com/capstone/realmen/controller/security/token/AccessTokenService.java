package com.capstone.realmen.controller.security.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.realmen.controller.handler.exceptions.AccessTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccessTokenService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.expired.time}")
    private Long expiredTime;
    private Date now = new Date();

    public String generateJwtToken(String identify) {

        return Jwts.builder().signWith(Keys.hmacShaKeyFor(secretKey.getBytes())).setSubject(identify)
                .setExpiration(Date.from(expiredTime())).setIssuedAt(now).compact();
    }

    private Instant expiredTime() {
        return Instant.now().plus(expiredTime, ChronoUnit.HOURS);
    }

    public Long getJwtDuration() {
        return ChronoUnit.MINUTES.between(Instant.now(), expiredTime());
    }

    public String getIdentify(String jwt) {
        return getClaims(jwt).getSubject();
    }

    public String getIdentifyFromHeader(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");
        if (Objects.nonNull(jwt)) {
            String formattedJwt = jwt.substring(7);
            if (validateToken(formattedJwt)) {
                return getIdentify(formattedJwt);
            }
        }
        return null;
    }

    public Boolean validateToken(String jwt) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
                    .parse(jwt);
            return true;
        } catch (MalformedJwtException exc) {
            throw new AccessTokenException();
        } catch (SignatureException exc) {
            throw new AccessTokenException();
        } catch (ExpiredJwtException exc) {
            throw new AccessTokenException();
        }
    }

    private Claims getClaims(String jwt) {
        return (Claims) Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build()
                .parse(jwt).getBody();
    }
}
