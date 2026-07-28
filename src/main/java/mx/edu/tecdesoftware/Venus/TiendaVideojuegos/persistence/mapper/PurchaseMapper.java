package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.mapper;

import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Purchase;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(source = "idcompra", target = "purchaseId")
    @Mapping(source = "idcliente", target = "clientId")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "mediopago", target = "paymentMethod")
    Purchase toPurchase(Compra compra);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "videojuegos", ignore = true)
    Compra toEntity(Purchase purchase);
}