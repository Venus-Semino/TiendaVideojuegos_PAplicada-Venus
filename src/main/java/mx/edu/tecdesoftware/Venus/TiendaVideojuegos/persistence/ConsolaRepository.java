package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Console;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.ConsoleRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud.ConsolaCrudRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Consola;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper.ConsoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsolaRepository implements ConsoleRepository {

    @Autowired
    private ConsolaCrudRepository consolaCrudRepository;

    @Autowired
    private ConsoleMapper mapper;

    @Override
    public List<Console> getAll() {
        List<Consola> consolas = (List<Consola>) consolaCrudRepository.findAll();
        return mapper.toConsoles(consolas);
    }

    @Override
    public Optional<List<Console>> getByBrand(String brand) {
        List<Consola> consolas = consolaCrudRepository.findByMarca(brand);
        return consolas.isEmpty() ? Optional.empty() : Optional.of(mapper.toConsoles(consolas));
    }

    @Override
    public Optional<Console> getConsole(Integer consoleId) {
        return consolaCrudRepository.findById(consoleId).map(consola -> mapper.toConsole(consola));
    }

    @Override
    public Console save(Console console) {
        Consola consola = mapper.toEntity(console);
        return mapper.toConsole(consolaCrudRepository.save(consola));
    }
    @Override
    public Optional<Console> getByName(String name) {
        return consolaCrudRepository.findByNombre(name).map(consola -> mapper.toConsole(consola));
    }

    @Override
    public void delete(Integer consoleId) {
        consolaCrudRepository.deleteById(consoleId);
    }
}