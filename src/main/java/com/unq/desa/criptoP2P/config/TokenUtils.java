package com.unq.desa.criptoP2P.config;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    private final static String TOKEN_SECRET = "4pE8z3PBoHjnV1AhvGk+e8h2p+ShZpOnpr8cwHmMh1w=";
    private final static Long TOKEN_VALID_SECOND = 120000L ;

    public static String generateToken(String nombre , String mail){
        var expiration = TOKEN_VALID_SECOND + 1_000L;
        var dataExpiration = new Date(System.currentTimeMillis() + expiration);
        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre", nombre);
    return Jwts.builder().setSubject(mail).setExpiration(dataExpiration)
            .addClaims(extra).signWith(Keys.hmacShaKeyFor(TOKEN_SECRET.getBytes())).compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            var claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            var mail = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(mail, null, Collections.emptyList());
        }catch (JwtException r){
            return null;
        }
    }
}
