package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.Client;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") // Inyección en el contexto de Spring
public interface ClientMapper {

    // Convirtiendo de Entidad a Dominio
    @Mapping(source = "nombre", target = "firstName")
    @Mapping(source = "apellidos", target = "lastName")
    @Mapping(source = "celular", target = "phone")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "correoElectronico", target = "email")
    Client toClient(Cliente cliente);

    // Convirtiendo de Dominio a Entidad bidireccionalmente
    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true) // Ignoramos la lista porque no está en el POJO
    Cliente toEntity(Client client);
}
