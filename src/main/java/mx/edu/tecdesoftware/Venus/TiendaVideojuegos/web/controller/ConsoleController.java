package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Console;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consoles")
@Tag(name="Console", description = "Manage consoles in the store")
public class ConsoleController {

    // Solo inyectamos el Service, manteniendo la arquitectura limpia
    @Autowired
    private ConsoleService consoleService;

    @GetMapping()
    @Operation(
            summary = "Get all consoles",
            description = "Return a list of all available consoles"
    )
    @ApiResponse(responseCode = "200", description = "Successful retrieval of consoles")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Console>> getAll(){
        return new ResponseEntity<>(consoleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get console by ID",
            description = "Return a console by its ID if it exists")
    @ApiResponse( responseCode = "200", description = "Console found" )
    @ApiResponse( responseCode = "404", description = "Console not found" )
    @ApiResponse( responseCode = "500", description = "Internal server error" )
    public ResponseEntity<Console> getConsole(
            @Parameter(description = "ID of the console retrieved", example = "1", required = true)
            @PathVariable("id") int consoleId){

        return consoleService.getConsole(consoleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/brand/{brand}")
    @Operation( summary = "Get consoles by brand", description = "Return all consoles from a specific brand" )
    @ApiResponse( responseCode = "200", description = "Consoles found for the brand" )
    @ApiResponse( responseCode = "404", description = "Consoles not found for the brand" )
    @ApiResponse( responseCode = "500", description = "Internal server error" )
    public ResponseEntity<List<Console>> getByBrand(
            @PathVariable @Parameter (description = "Brand name", example = "Nintendo", required = true) String brand){

        return consoleService.getByBrand(brand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Save a new console", description = "Register a new console and return the created entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example console",
                                    value = """
                                             {
                                                "name" : "Nintendo Switch OLED",
                                                "brand" : "Nintendo"
                                             }
                                             """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Console created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid console data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Console> save(@RequestBody Console console){
        return new ResponseEntity<>(consoleService.save(console), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a console by ID", description = "Delete a console if it exists")
    @ApiResponse(responseCode = "200", description = "Console deleted successfully")
    @ApiResponse(responseCode = "404", description = "Console not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the console to be deleted", example = "1", required = true)
            @PathVariable("id") int consoleId){

        if (consoleService.delete(consoleId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}