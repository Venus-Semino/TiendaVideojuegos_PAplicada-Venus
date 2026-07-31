package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Client;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Client", description = "Manage clients in the store")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {


    @Autowired
    private ClientService clientService;

    @GetMapping()
    @Operation(summary = "Get all clients", description = "Return a list of all registered clients")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of clients")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Return a client by their ID if they exist")
    @ApiResponse(responseCode = "200", description = "Client found")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "ID of the client retrieved", example = "1", required = true)
            @PathVariable("id") int clientId) {
        return clientService.getClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get a client by email", description = "Return a client using their registered email address")
    @ApiResponse(responseCode = "200", description = "Client found")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> getByEmail(
            @PathVariable @Parameter(description = "Email of the client", example = "venus@example.com", required = true) String email) {
        return clientService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Save a new client", description = "Register a new client and return the created entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example client",
                                    value = """
                                             {
                                                "firstName" : "Juan",
                                                "lastName" : "Pérez",
                                                "phone" : "5551234567",
                                                "address" : "Av. Tecnológico 123",
                                                "email" : "juan.perez@example.com"
                                             }
                                             """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Client created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid client data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID", description = "Delete a client if they exist")
    @ApiResponse(responseCode = "200", description = "Client deleted successfully")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the client to be deleted", example = "1", required = true)
            @PathVariable("id") int clientId) {
        if (clientService.delete(clientId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}