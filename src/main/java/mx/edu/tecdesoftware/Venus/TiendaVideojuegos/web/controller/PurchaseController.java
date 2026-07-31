package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Purchase;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name="Purchase", description = "Manage purchases in the store")
@SecurityRequirement(name = "bearerAuth")

public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    @Operation(summary = "Get all purchases", description = "Return a list of all purchases made")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of purchases")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get purchase by ID", description = "Return a purchase by its ID if it exists")
    @ApiResponse(responseCode = "200", description = "Purchase found")
    @ApiResponse(responseCode = "404", description = "Purchase not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> getPurchase(
            @Parameter(description = "ID of the purchase retrieved", example = "1", required = true)
            @PathVariable("id") int purchaseId) {
        return purchaseService.getPurchase(purchaseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get purchases by client", description = "Return all purchases made by a specific client")
    @ApiResponse(responseCode = "200", description = "Purchases found for the client")
    @ApiResponse(responseCode = "404", description = "Purchases not found for the client")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @PathVariable @Parameter(description = "Client ID", example = "1", required = true) int clientId) {
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Save a new purchase", description = "Register a new purchase and its items",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example purchase",
                                    value = """
                                             {
                                                "clientId" : 1,
                                                "paymentMethod" : "Tarjeta",
                                                "items" : [
                                                    {
                                                        "gameId" : 4,
                                                        "quantity" : 2
                                                    }
                                                ]
                                             }
                                             """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Purchase created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}