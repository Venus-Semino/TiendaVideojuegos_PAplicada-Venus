package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Client;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    // Entidad a Dominio
    @Mapping(source = "nombre", target = "firstName")
    @Mapping(source = "apellidos", target = "lastName")
    @Mapping(source = "celular", target = "phone")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "correoElectronico", target = "email")
    Client toClient(Cliente cliente);

    // Dominio a Entidad bidireccionalmente
    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true)
    Cliente toEntity(Client client);
    List<Client> toClients(List<Cliente> clientes);
}
