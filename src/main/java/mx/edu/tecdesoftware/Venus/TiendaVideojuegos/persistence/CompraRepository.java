package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Purchase;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Compra;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(Integer clientId) {
        List<Compra> compras = compraCrudRepository.findByIdCliente(clientId);
        return compras.isEmpty() ? Optional.empty() : Optional.of(mapper.toPurchases(compras));
    }

    @Override
    public Optional<Purchase> getPurchase(Integer purchaseId) {
        return compraCrudRepository.findById(purchaseId).map(compra -> mapper.toPurchase(compra));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toEntity(purchase);
        if (compra.getFecha() == null) {
            compra.setFecha(LocalDateTime.now());
        }
        if (compra.getVideojuegos() != null) {
            compra.getVideojuegos().forEach(videojuego -> videojuego.setCompra(compra));
        }

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}