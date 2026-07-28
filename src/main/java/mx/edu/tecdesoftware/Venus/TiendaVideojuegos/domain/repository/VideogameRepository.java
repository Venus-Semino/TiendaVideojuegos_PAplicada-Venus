package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Videogame;

import java.util.List;
import java.util.Optional;


public interface VideogameRepository {
    List<Videogame> getAll();
    Optional<Videogame> getById(Integer id);
    Optional<Videogame> getByTitle(String title);
    Videogame save(Videogame videogame);
    void delete(Integer id);
}
