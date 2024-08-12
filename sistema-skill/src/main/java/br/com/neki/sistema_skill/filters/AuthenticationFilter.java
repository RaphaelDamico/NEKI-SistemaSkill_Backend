package br.com.neki.sistema_skill.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.neki.sistema_skill.entities.User;
import br.com.neki.sistema_skill.exceptions.EntityNotFoundException;
import br.com.neki.sistema_skill.repositories.UserRepository;
import br.com.neki.sistema_skill.services.JwtTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    private static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/health-check",
            "/auth/signin",
            "/users/signup",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // Verifica se a URL está na lista de URLs excluídas
        if (isExcluded(requestURI)) {
            System.out.println("Request to excluded url: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String token = recoveryToken(request); // Pega o token do Header Authorization, na requisição
        if (token != null) {
            try {
                String subject = jwtTokenService.getSubjectFromToken(token);
                // Pega o subject do Jwt - ou seja, o e-mail do usuário
                User user = userRepository.findByUsername(subject).orElseThrow(
                        () -> new EntityNotFoundException("No user found with id: " + subject));// Recupera os dados do Usuário através do seu e-mail

                if (user != null) {
                    // Gera um "Authentication" (objeto de autenticação do Spring Security)
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                            user.getAuthorities());

                    // Seta o objeto de autenticação no contexto de segurança do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Authentication successful for the user: " + user.getUsername());
                } else {
                    System.out.println("User not found with token: " + token); 
                }
            } catch (Exception e) {
                System.out.println("Error verifying the token: " + e.getMessage());
            }
        } else {
            System.out.println("Token not found in the request header: " + requestURI);
        }

        // Dá prosseguimento no processamento da requisição
        filterChain.doFilter(request, response);
    }

    private boolean isExcluded(String requestURI) {
        return EXCLUDED_URLS.stream().anyMatch(url -> requestURI.startsWith(url));
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}