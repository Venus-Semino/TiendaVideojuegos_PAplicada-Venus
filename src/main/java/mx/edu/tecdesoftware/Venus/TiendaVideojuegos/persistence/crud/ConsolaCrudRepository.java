package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Consola;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ConsolaCrudRepository extends CrudRepository<Consola, Integer> {
    List<Consola> findByMarca(String marca);
    Optional<Consola> findByNombre(String nombre);
}