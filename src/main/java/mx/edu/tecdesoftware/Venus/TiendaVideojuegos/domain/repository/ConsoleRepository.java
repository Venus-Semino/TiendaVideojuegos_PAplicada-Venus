package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Console;
import java.util.List;
import java.util.Optional;

public interface ConsoleRepository {
    List<Console> getAll();
    Optional<List<Console>> getByBrand(String brand);
    Optional<Console> getConsole(Integer consoleId);
    Console save(Console console);
    void delete(Integer consoleId);
}