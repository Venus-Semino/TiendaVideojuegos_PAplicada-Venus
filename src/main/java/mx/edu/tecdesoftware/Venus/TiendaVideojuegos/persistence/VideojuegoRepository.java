package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Videogame;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.VideogameRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud.VideojuegoCrudRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Videojuego;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper.VideogameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VideojuegoRepository implements VideogameRepository {

    @Autowired
    private VideojuegoCrudRepository videojuegoCrudRepository;

    @Autowired
    private VideogameMapper mapper;

    @Override
    public List<Videogame> getAll() {
        List<Videojuego> videojuegos = (List<Videojuego>) videojuegoCrudRepository.findAll();
        // Asegúrate de tener toVideogames (en plural) en tu VideogameMapper
        return mapper.toVideogames(videojuegos);
    }

    @Override
    public Optional<Videogame> getById(Integer id) {
        return videojuegoCrudRepository.findById(id).map(videojuego -> mapper.toVideogame(videojuego));
    }

    @Override
    public Optional<Videogame> getByTitle(String title) {
        return videojuegoCrudRepository.findByTitulo(title).map(videojuego -> mapper.toVideogame(videojuego));
    }

    @Override
    public Videogame save(Videogame videogame) {
        Videojuego videojuego = mapper.toEntity(videogame);
        return mapper.toVideogame(videojuegoCrudRepository.save(videojuego));
    }

    @Override
    public void delete(Integer id) {
        videojuegoCrudRepository.deleteById(id);
    }
}