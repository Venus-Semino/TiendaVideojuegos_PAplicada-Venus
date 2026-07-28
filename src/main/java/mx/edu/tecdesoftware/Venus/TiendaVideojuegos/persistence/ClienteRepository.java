package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Client;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.ClientRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Cliente;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<Client> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return mapper.toClients(clientes);
    }

    @Override
    public Optional<Client> getClient(Integer clientId) {
        return clienteCrudRepository.findById(clientId).map(cliente -> mapper.toClient(cliente));
    }

    @Override
    public Optional<Client> getByEmail(String email) {
        return clienteCrudRepository.findByCorreoElectronico(email).map(cliente -> mapper.toClient(cliente));
    }

    @Override
    public Client save(Client client) {
        Cliente cliente = mapper.toEntity(client);
        return mapper.toClient(clienteCrudRepository.save(cliente));
    }

    @Override
    public void delete(Integer clientId) {
        clienteCrudRepository.deleteById(clientId);
    }
}