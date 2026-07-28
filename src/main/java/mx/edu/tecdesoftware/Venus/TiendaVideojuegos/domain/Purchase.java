package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain;

import java.time.LocalDateTime;

public class Purchase {
    private Integer purchaseId; // Uso estricto de Integer
    private Integer clientId;
    private LocalDateTime date;
    private String paymentMethod;

    // GETTERS AND SETTERS

    public Integer getPurchaseId() { return purchaseId; }

    public void setPurchaseId(Integer purchaseId) { this.purchaseId = purchaseId; }

    public Integer getClientId() { return clientId; }

    public void setClientId(Integer clientId) { this.clientId = clientId; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public String getPaymentMethod() { return paymentMethod; }

    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}