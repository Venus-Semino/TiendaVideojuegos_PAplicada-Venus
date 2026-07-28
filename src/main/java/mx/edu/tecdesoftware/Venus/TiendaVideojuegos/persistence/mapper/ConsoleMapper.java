package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Console;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Consola;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsoleMapper {

    // Entidad a Dominio
    @Mapping(source = "id", target = "consoleId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "marca", target = "brand")
    Console toConsole(Consola consola);

    // Dominio a Entidad
    @InheritInverseConfiguration
    @Mapping(target = "videojuegos", ignore = true)
    Consola toEntity(Console console);

    List<Console> toConsoles(List<Consola> consolas);
}