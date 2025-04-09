package br.gov.mt.seplag.servidores.controller;

import br.gov.mt.seplag.servidores.dto.RegisterRequest;
import br.gov.mt.seplag.servidores.security.JwtService;
import br.gov.mt.seplag.servidores.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import br.gov.mt.seplag.servidores.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request) {
        User savedUser = userService.createUser(
                request.getUsername(),
                request.getPassword(),
                request.getRole()
        );

        return ResponseEntity.ok("Usuário criado com sucesso: " + savedUser.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos.");
        }
    }
}
