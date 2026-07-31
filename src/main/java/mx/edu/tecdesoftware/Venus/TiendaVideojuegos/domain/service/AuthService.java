package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Client;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public Optional<String> login(String email, String rawPassword) {
        Optional<Client> clientOpt = clientService.getByEmail(email);

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();

            // --- DEPURACIÓN TEMPORAL (Borrar antes de tu commit) ---
            System.out.println("Correo encontrado: " + client.getEmail());
            System.out.println("Hash en base de datos: [" + client.getPassword() + "]");
            System.out.println("¿Coinciden 1234 con el hash?: " + passwordEncoder.matches(rawPassword, client.getPassword()));
            // -------------------------------------------------------

            if (passwordEncoder.matches(rawPassword, client.getPassword())) {
                String token = jwtUtil.generateToken(client.getEmail());
                return Optional.of(token);
            }
        } else {
            System.out.println("ERROR: No se encontró ningún cliente con el correo " + email);
        }
        return Optional.empty();
    }
}