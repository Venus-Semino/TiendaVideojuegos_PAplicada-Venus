package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.repository;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(Integer clientId);
    Optional<Purchase> getPurchase(Integer purchaseId);
    Purchase save(Purchase purchase);
}