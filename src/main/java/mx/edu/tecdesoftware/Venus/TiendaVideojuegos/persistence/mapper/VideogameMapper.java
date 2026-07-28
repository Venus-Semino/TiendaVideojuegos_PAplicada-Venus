package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Videogame;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Videojuego;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VideogameMapper {

    // Entidad a Dominio
    @Mapping(source = "id", target = "gameId")
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "consola.id", target = "consoleId")
    Videogame toVideogame(Videojuego videojuego);

    // Dominio a Entidad
    @InheritInverseConfiguration
    @Mapping(target = "consola.id", source = "consoleId")
    Videojuego toEntity(Videogame videogame);

    // Traducción de listas
    List<Videogame> toVideogames(List<Videojuego> videojuegos);
}