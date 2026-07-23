package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private String idcompra;

    @Column(name = "id_cliente")
    private String idcliente;

    private LocalDateTime fecha;

    @Column(name = "medio_pago")
    private String mediopago;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Compravideojuego> videojuegos;
}
