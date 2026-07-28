package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getAll();
    Optional<Client> getClient(Integer clientId);
    Optional<Client> getByEmail(String email);
    Client save(Client client);
    void delete(Integer clientId);
}