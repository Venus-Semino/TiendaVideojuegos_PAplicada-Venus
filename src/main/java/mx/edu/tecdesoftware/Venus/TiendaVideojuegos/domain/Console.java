package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain;

public class Console {
    private Integer consoleId;
    private String name;
    private String brand;

    // Genera los Getters y Setters
    public Integer getConsoleId() { return consoleId; }
    public void setConsoleId(Integer consoleId) { this.consoleId = consoleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
}