package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "consolas")
public class Consola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer idConsola;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "marca")
    private String marca;

    // Relación Maestro-Detalle con persistencia en cascada
    @OneToMany(mappedBy = "consola", cascade = ALL)
    private List<Videojuego> videojuegos;

    // Getters y Setters
    public Integer getId() { return idConsola; }
    public void setId(Integer id) { this.idConsola = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public List<Videojuego> getVideojuegos() { return videojuegos; }
    public void setVideojuegos(List<Videojuego> videojuegos) { this.videojuegos = videojuegos; }
}