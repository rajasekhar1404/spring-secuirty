package com.krs.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class KrsFilters extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("x-access-token");

        String uri = request.getRequestURI();

        if ("/".equals(uri) || "/reg".equals(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        if ("RAJA".equals(accessToken)) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("RAJA", "", List.of(new SimpleGrantedAuthority("ADMIN")));
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
            return;
        } else {
            response.setStatus(200);
            response.getWriter().println("You are not authorized");
            return;
        }
    }
}