package com.boarding.springsecurityjwt.Configuration;

import com.boarding.springsecurityjwt.Services.JWTService;
import com.boarding.springsecurityjwt.Services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNullApi;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String userName;

            if(StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer")){
                filterChain.doFilter(request, response);
                return;
            }

            jwt= authHeader.substring(7);
            System.out.println("~~~>JWT "+ jwt);
            userName= jwtService.extractUsername(jwt);
            long id= jwtService.extractId(jwt);
            System.out.println("~~~>FILTER ");
            System.out.println("~~~~~~>userName "+ userName);
            System.out.println("~~~~~~>id "+ id);

            if(StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails= userService.userDetailsService().loadUserByUsername(userName);
                System.out.println("~~~Present User~~~> " + userDetails.toString());
                if(jwtService.isTokenValid(jwt,userDetails)){
                    SecurityContext securityContext= SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(
                            userDetails,null,userDetails.getAuthorities()
                    );

                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    securityContext.setAuthentication(token);

                    SecurityContextHolder.setContext(securityContext);
                    System.out.println("Context "+ SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                }
            }
            filterChain.doFilter(request, response);
        }catch (Exception ex){
            throw new RuntimeException(ex.getCause());
        }
    }
}
