package com.example.proyectoregistro.filters;

import com.example.proyectoregistro.service.JwtUtilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtUtilService jwtUtilService;
    @Autowired
    private ObjectMapper objectMapper;

    // Lista de rutas que no requieren autenticación
    private static final List<String> excludedUrls = Arrays.asList(
            "/public"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Obtener la URL de la solicitud
        String requestUrl = request.getRequestURI();

        // Verificar si la URL está en la lista de exclusiones
        if (isUrlExcluded(requestUrl)) {
            // La URL está en la lista de exclusiones, continuar con la cadena de filtros sin verificar el token
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // El token no está presente o no está en el formato correcto
            sendErrorResponse(response, "Se requiere un token de autenticación válido.");
            return;
        }

        String jwt = authorizationHeader.substring(7);
        String username = jwtUtilService.extractUsername(jwt);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtilService.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorObject errorObject = new ErrorObject(errorMessage);
        String errorJson = objectMapper.writeValueAsString(errorObject);

        response.getWriter().write(errorJson);
    }

    private boolean isUrlExcluded(String requestUrl) {
        // Verificar si la URL está en la lista de exclusiones
        return excludedUrls.stream().anyMatch(requestUrl::startsWith);
    }

    @AllArgsConstructor
    private static class ErrorObject {
        @Getter @Setter
        private String errorMessage;
    }
}
