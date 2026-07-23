package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.Console;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Consola;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Inyección en Spring
public interface ConsoleMapper {

    // Entidad a Dominio
    @Mapping(source = "id", target = "consoleId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "marca", target = "brand")
    Console toConsole(Consola consola);

    // Dominio a Entidad
    @InheritInverseConfiguration
    @Mapping(target = "videojuegos", ignore = true) // Ignoramos la lista porque no está en el dominio
    Consola toEntity(Console console);
}
