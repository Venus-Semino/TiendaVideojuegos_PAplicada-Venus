package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Consola;
import org.springframework.data.repository.CrudRepository;

public interface ConsolaCrudRepository extends CrudRepository<Consola, Integer> {
}