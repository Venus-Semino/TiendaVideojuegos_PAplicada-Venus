package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
}