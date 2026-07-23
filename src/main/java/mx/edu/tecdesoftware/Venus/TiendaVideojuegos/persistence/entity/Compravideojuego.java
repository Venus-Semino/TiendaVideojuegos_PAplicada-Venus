package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compras_videojuegos")
public class Compravideojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_videojuego", nullable = false)
    private Videojuego videojuego;

    private Integer cantidad;

    // GETTERS AND SETTERS
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }

    public Videojuego getVideojuego() { return videojuego; }
    public void setVideojuego(Videojuego videojuego) { this.videojuego = videojuego; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
