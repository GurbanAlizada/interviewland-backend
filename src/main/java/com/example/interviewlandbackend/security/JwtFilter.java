package com.example.interviewlandbackend.security;

import com.example.interviewlandbackend.service.UserDetailsServiceImpl;
import com.example.interviewlandbackend.util.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtFilter extends OncePerRequestFilter {


    private final TokenGenerator tokenGenerator;
    private final UserDetailsServiceImpl userDetailsService;



    public JwtFilter(TokenGenerator tokenGenerator,
                     UserDetailsServiceImpl userDetailsService) {
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);
        String username;


        try {

            if (!token.isEmpty()) {
                username = tokenGenerator.verifyJWT(token).getSubject();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

            filterChain.doFilter(request, response);


        }catch (Exception exception){

            sendError(response , exception);

        }


    }





    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null  ||  !header.startsWith("Bearer ")) {
            return "";
        }
        return header.substring(7);
    }



    private void sendError(HttpServletResponse res, Exception e) throws IOException {
        res.setContentType("application/json");
        Map<String, String> errors = new HashMap<>();
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        errors.put("error", e.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        res.getWriter().write(mapper.writeValueAsString(errors));
    }



}
