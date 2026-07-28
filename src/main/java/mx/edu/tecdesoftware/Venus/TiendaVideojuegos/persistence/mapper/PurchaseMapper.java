package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Purchase;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(source = "idCompra", target = "purchaseId") 
    @Mapping(source = "idCliente", target = "clientId")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "medioPago", target = "paymentMethod")
    Purchase toPurchase(Compra compra);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "videojuegos", ignore = true)
    Compra toEntity(Purchase purchase);

    List<Purchase> toPurchases(List<Compra> compras);
}