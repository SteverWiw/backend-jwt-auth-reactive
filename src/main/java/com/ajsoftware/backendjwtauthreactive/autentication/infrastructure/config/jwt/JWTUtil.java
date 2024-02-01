package com.ajsoftware.backendjwtauthreactive.autentication.infrastructure.config.jwt;

import com.ajsoftware.backendjwtauthreactive.autentication.domain.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWTUtil {

    @Value("${application.jwt.jwt-secret}")
    private String secret;
    private Key key;

    private SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getToken(UserEntity user){
        return generateToken(new HashMap<>(),user);
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public String getUserNameFromToken(String token) {
         return getClaim(token,Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaim(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token)
        {
            return getExpirationDateFromToken(token).before(new Date());
        }

    private String generateToken(Map<String,Object> extraClaims, UserEntity user) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .claim(JwtClaim.FIRST_NAME.name(),user.getPerson().getFirstName())
                .claim(JwtClaim.SECOND_NAME.name(), user.getPerson().getSecondName())
                .claim(JwtClaim.FIRST_SURNAME.name(), user.getPerson().getFirstSurname())
                .claim(JwtClaim.SECOND_SURNAME.name(), user.getPerson().getSecondSurname())
                .claim(JwtClaim.EMAIL.name(), user.getPerson().getEmail())
                .claim(JwtClaim.ROLE_NAME.name(), user.getRole().getDescription())
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
                .signWith(getKey()).compact();
    }


    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
