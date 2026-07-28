package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Videogame;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;

    public List<Videogame> getAll() {
        return videogameRepository.getAll();
    }

    public Optional<Videogame> getById(Integer id) {
        return videogameRepository.getById(id);
    }

    public Optional<Videogame> getByTitle(String title) {
        return videogameRepository.getByTitle(title);
    }

    public Videogame save(Videogame videogame) {
        return videogameRepository.save(videogame);
    }

    public boolean delete(Integer id) {
        return getById(id).map(videogame -> {
            videogameRepository.delete(id);
            return true;
        }).orElse(false);
    }
}