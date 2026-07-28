package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Videojuego;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VideojuegoCrudRepository extends CrudRepository<Videojuego, Integer> {
    Optional<Videojuego> findByTitulo(String titulo);
}