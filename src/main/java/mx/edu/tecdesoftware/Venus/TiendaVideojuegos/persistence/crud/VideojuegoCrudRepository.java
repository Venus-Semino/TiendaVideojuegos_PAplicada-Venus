package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Videojuego;
import org.springframework.data.repository.CrudRepository;

public interface VideojuegoCrudRepository extends CrudRepository<Videojuego, Integer> {
}