package com.unq.desa.criptoP2P.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unq.desa.criptoP2P.service.UserDetailImplService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        var authCredentials = new AuthCredential();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
        }catch (Exception e){
        }
        UsernamePasswordAuthenticationToken userP = new UsernamePasswordAuthenticationToken(
                authCredentials.getMail(),authCredentials.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(userP);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        var user = (UserDetailImplService) authResult.getPrincipal();
         String token = TokenUtils.generateToken(user.getNombre(), user.getUsername());
         response.addHeader("Authorization","Bearer "+ token);
         response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
