package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idVideojuego;

    @Column(name = "titulo")
    private String titulo;

    @Column(nullable = false)
    private Double precio;

    // Relación de vuelta hacia Consola
    @ManyToOne
    @JoinColumn(name = "consola_id", nullable = false)
    private Consola consola;

    // Getters y Setters
    public Integer getId() { return idVideojuego; }
    public void setId(Integer id) { this.idVideojuego = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Consola getConsola() { return consola; }
    public void setConsola(Consola consola) { this.consola = consola; }
}