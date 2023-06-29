package com.example.proyectoregistro.controllers;

import com.example.proyectoregistro.model.AuthenticationReq;
import com.example.proyectoregistro.model.TokenInfo;
import com.example.proyectoregistro.service.JwtUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    UserDetailsService usuarioDetailsService;
    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @PostMapping("/public/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
        
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenInfo(jwt));
    }
}
