package mx.edu.tecdesoftware.Venus.TiendaVideojuegos.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.Videogame;
import mx.edu.tecdesoftware.Venus.TiendaVideojuegos.domain.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@Tag(name="Videogame", description = "Manage videogames in the store")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @GetMapping()
    @Operation(summary = "Get all videogames", description = "Return a list of all available videogames")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of videogames")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<Videogame>> getAll() {
        return new ResponseEntity<>(videogameService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get videogame by ID", description = "Return a videogame by its ID if it exists")
    @ApiResponse( responseCode = "200", description = "Videogame found" )
    @ApiResponse( responseCode = "404", description = "Videogame not found" )
    @ApiResponse( responseCode = "500", description = "Internal server error" )
    public ResponseEntity<Videogame> getVideogame(
            @Parameter(description = "ID of the videogame retrieved", example = "10", required = true)
            @PathVariable("id") int gameId) {
        return videogameService.getById(gameId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/title/{title}")
    @Operation( summary = "Get a videogame by title", description = "Return a videogame with a specific title" )
    @ApiResponse( responseCode = "200", description = "Videogame found" )
    @ApiResponse( responseCode = "404", description = "Videogame not found" )
    @ApiResponse( responseCode = "500", description = "Internal server error" )
    public ResponseEntity<Videogame> getByTitle(
            @PathVariable @Parameter(description = "Title of the videogame", example = "Halo", required = true) String title) {
        return videogameService.getByTitle(title)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Save a new videogame", description = "Register a new videogame and return the created entity",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Example videogame",
                                    value = """
                                             {
                                                "title" : "Halo Infinite",
                                                "price" : 1200.00,
                                                "consoleId" : 2
                                             }
                                             """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Videogame created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid videogame data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Videogame> save(@RequestBody Videogame videogame) {
        return new ResponseEntity<>(videogameService.save(videogame), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a videogame by ID", description = "Delete a videogame if it exists")
    @ApiResponse(responseCode = "200", description = "Videogame deleted successfully")
    @ApiResponse(responseCode = "404", description = "Videogame not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the videogame to be deleted", example = "10", required = true)
            @PathVariable("id") int gameId) {
        if (videogameService.delete(gameId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}