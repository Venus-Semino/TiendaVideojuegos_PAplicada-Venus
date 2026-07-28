package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain;

public class Videogame {
    private Integer gameId;
    private String title;
    private Double price;
    private Integer consoleId;

    // GETTERS AND SETTERS


    public Integer getGameId() { return gameId; }

    public void setGameId(Integer gameId) { this.gameId = gameId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Integer getConsoleId() { return consoleId; }

    public void setConsoleId(Integer consoleId) { this.consoleId = consoleId; }
}