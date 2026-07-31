package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClientService clientService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("\n========== NUEVA PETICIÓN ==========");

        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        String jwt = null;
        String email = null;

        // Verificar si existe el encabezado Authorization
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            jwt = authHeader.substring(7);
            System.out.println("Token recibido: " + jwt);

            try {
                email = jwtUtil.extractEmail(jwt);
                System.out.println("Email extraído: " + email);
            } catch (Exception e) {
                System.out.println("ERROR al extraer el email:");
                e.printStackTrace();
            }
        } else {
            System.out.println("No se recibió un token Bearer.");
        }

        // Si existe email y aún no hay autenticación
        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            var clientOptional = clientService.getByEmail(email);

            System.out.println("Usuario encontrado en BD: " + clientOptional.isPresent());

            if (clientOptional.isPresent()) {

                boolean valido = jwtUtil.validateToken(jwt, email);
                System.out.println("¿Token válido?: " + valido);

                if (valido) {

                    CustomUserDetails userDetails =
                            new CustomUserDetails(clientOptional.get());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authToken);

                    System.out.println("Autenticación registrada correctamente.");
                    System.out.println("Authentication: "
                            + SecurityContextHolder.getContext().getAuthentication());
                }

            } else {

                System.out.println("El usuario NO existe en la base de datos.");

            }

        } else {

            System.out.println("No se pudo autenticar porque el email es null o ya había autenticación.");

        }

        System.out.println("========== FIN FILTRO ==========\n");

        filterChain.doFilter(request, response);
    }
}