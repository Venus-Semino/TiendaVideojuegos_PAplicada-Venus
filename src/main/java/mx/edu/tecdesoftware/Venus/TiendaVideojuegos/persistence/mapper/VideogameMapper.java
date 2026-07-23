package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.Videogame;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Videojuego;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideogameMapper {

    // Entidad a Dominio
    @Mapping(source = "id", target = "gameId")
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "consola.id", target = "consoleId") // Sacamos el ID directamente del objeto consola
    Videogame toVideogame(Videojuego videojuego);

    // Dominio a Entidad
    @InheritInverseConfiguration
    @Mapping(target = "consola", ignore = true) // Ignoramos el objeto complejo en el mapeo inverso
    Videojuego toEntity(Videogame videogame);
}