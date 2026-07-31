package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.dto.AuthResponse;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.dto.LoginRequest;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints to obtain JWT tokens")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Validates credentials and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Successful authentication")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword())
                .map(token -> ResponseEntity.ok(new AuthResponse(token)))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}