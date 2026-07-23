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
    private Integer idcompra;

    @Column(name = "id_cliente")
    private Integer idcliente;

    private LocalDateTime fecha;

    @Column(name = "medio_pago")
    private String mediopago;

    // Relación necesaria para el Mapper y para la base de datos
    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<Compravideojuego> videojuegos;

    // --- GETTERS Y SETTERS ---

    public Integer getIdcompra() { return idcompra; }
    public void setIdcompra(Integer idcompra) { this.idcompra = idcompra; }

    public Integer getIdcliente() { return idcliente; }
    public void setIdcliente(Integer idcliente) { this.idcliente = idcliente; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getMediopago() { return mediopago; }
    public void setMediopago(String mediopago) { this.mediopago = mediopago; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Compravideojuego> getVideojuegos() { return videojuegos; }
    public void setVideojuegos(List<Compravideojuego> videojuegos) { this.videojuegos = videojuegos; }
}