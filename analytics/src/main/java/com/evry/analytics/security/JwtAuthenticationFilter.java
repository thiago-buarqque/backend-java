package com.evry.analytics.security;

import com.evry.analytics.service.UserService;
import com.evry.analytics.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if(StringUtils.isEmpty(authorization) || !StringUtils.startsWith(authorization, "Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authorization.substring(7);

            String userEmail = jwtService.extractUserName(jwt);

            if(StringUtils.isNotEmpty(userEmail) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                setContextAuthentication(request, jwt, userEmail, userService,
                                         jwtService);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException expiredJwtException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"Your token is expired.\"}");
        }

    }

    private static void setContextAuthentication(
            HttpServletRequest request, String jwt, String userEmail,
            UserService userService, JwtService jwtService) {

        UserDetailsService userDetailsService = userService.userDetailsService();

        UserDetails userDetails = userDetailsService.loadUserByUsername(
                userEmail);

        if(jwtService.isTokenValid(jwt, userDetails)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    request));

            context.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(context);
        }
    }
}
