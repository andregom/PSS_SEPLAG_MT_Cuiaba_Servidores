package br.gov.mt.seplag.servidores.controller;

import br.gov.mt.seplag.servidores.dto.AuthResponse;
import br.gov.mt.seplag.servidores.dto.RefreshTokenRequest;
import br.gov.mt.seplag.servidores.dto.RegisterRequest;
import br.gov.mt.seplag.servidores.model.RefreshToken;
import br.gov.mt.seplag.servidores.repository.RefreshTokenRepository;
import br.gov.mt.seplag.servidores.security.JwtService;
import br.gov.mt.seplag.servidores.service.UserService;
import br.gov.mt.seplag.servidores.service.CustomUserDetailsService;
import br.gov.mt.seplag.servidores.service.RefreshTokenService;

import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import br.gov.mt.seplag.servidores.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        User savedUser = userService.createUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );

        return ResponseEntity.ok("Usuário criado com sucesso: " + savedUser.getUsername());
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request) {
        try {
            RefreshToken token = refreshTokenService
                    .verifyExpiration(
                            refreshTokenRepository.findByToken(request.getRefreshToken())
                                    .orElseThrow(() -> new RuntimeException("Refresh token inválido"))
                    );

            UserDetails user = customUserDetailsService.loadUserByUsername(token.getUser().getUsername());

            String newAccessToken = jwtService.generateAccessToken(user);

            return ResponseEntity.ok(new AuthResponse(newAccessToken, token.getToken()));

        } catch (JwtException e) {
            return ResponseEntity.status(401).body("Refresh token inválido ou expirado.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails user = (UserDetails) auth.getPrincipal();

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = refreshTokenService.createRefreshToken(user.getUsername()).getToken();

            return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }

}
