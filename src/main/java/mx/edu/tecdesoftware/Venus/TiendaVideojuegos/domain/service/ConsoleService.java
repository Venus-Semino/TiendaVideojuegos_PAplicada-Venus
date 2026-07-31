package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Console;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    public List<Console> getAll() { return consoleRepository.getAll(); }

    public Optional<Console> getConsole(Integer consoleId) {
        return consoleRepository.getConsole(consoleId);
    }

    public Optional<List<Console>> getByBrand(String brand) {
        return consoleRepository.getByBrand(brand);
    }

    public Optional<Console> getByName(String name) {
        return consoleRepository.getByName(name);
    }

    public Console save(Console console) {
        if (getByName(console.getName()).isPresent()) {
            return null;
        }
        return consoleRepository.save(console);
    }

    public boolean delete(Integer consoleId) {
        return getConsole(consoleId).map(console -> {
            consoleRepository.delete(consoleId);
            return true;
        }).orElse(false);
    }
}