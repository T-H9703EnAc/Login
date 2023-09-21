package com.app.login.configurations;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.app.login.service.CustomUserDetailsService;
import com.app.login.service.PasswordVerificationService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordVerificationService passwordVerificationService;

    public CustomAuthenticationProvider(
        CustomUserDetailsService userDetailsService, 
        PasswordVerificationService passwordVerificationService
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordVerificationService = passwordVerificationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String providedPassword = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (passwordVerificationService.isPasswordValid(providedPassword, userDetails)) {
            return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("Authentication failed");
        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        // authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
