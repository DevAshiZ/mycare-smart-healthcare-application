package com.csse.mycare.security.config;

import com.csse.mycare.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        String path = request.getRequestURI();

        if (path.contains("/user/register") || path.contains("/user/authenticate")) {
            filterChain.doFilter(request, response); // Skip custom logic and continue
            log.trace("Skipping JWT Authentication for path: {}, request: {}", path, request);
            return;
        }

        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Authorization Header is missing.");
        }

        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationCredentialsNotFoundException("Invalid Authorization Header.");
        }

        jwtToken = authorizationHeader.substring(7);
        userEmail = jwtService.extractUsername(jwtToken);
        log.info("User logging in: {}", userEmail);

        // Check if user is not already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Check if token is valid
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,
                        userDetails.getAuthorities()
                );

                // Set details of the authentication
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication in the context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        try {
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + e.getMessage());
        }

    }
}
