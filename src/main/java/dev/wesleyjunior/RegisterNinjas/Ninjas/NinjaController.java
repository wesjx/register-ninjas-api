package dev.wesleyjunior.RegisterNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ninjas")
@Tag(name = "Ninjas", description = "Gerenciamento completo de ninjas do sistema")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all ninjas",
            description = """
            Returns a complete list of all registered ninjas.
            
            **Permissions:** Public endpoint
            
            **Response:**
            - `200`: Success - Returns List<NinjaDTO>
            
            **Example:**
            ```json
            [
              {
                "uuid": "123e4567-e89b-12d3-a456-426614174000",
                "name": "Naruto Uzumaki",
                "age": 16,
                "email": "naruto@konoha.br"
              }
            ]
            ```
            """
    )
    public ResponseEntity<List<NinjaDTO>> listNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/list/{uuid}")
    @Operation(
            summary = "Get ninja by ID",
            description = """
            Retrieves a specific ninja by its unique identifier (UUID).
            
            **Permissions:** Public endpoint
            
            **Responses:**
            - `200`: Ninja found successfully
            - `404`: Ninja not found
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja found successfully"),
            @ApiResponse(responseCode = "404", description = "Ninja not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> listNinjasById(@PathVariable UUID uuid) {
        NinjaDTO ninjaDTO = ninjaService.listNinjasById(uuid);
        if (ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja with ID: " + uuid + " not found.");
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new ninja",
            description = """
            Creates a new ninja in the system.
            
            **Permissions:** Authenticated users
            
            **Request Body:** NinjaDTO with name, age, email and other required fields
            
            **Responses:**
            - `201`: Ninja created successfully
            - `400`: Invalid request data
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> createNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.createNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja created successfully. Name: " + ninjaDTO.getName());
    }

    @DeleteMapping("/delete/{uuid}")
    @Operation(
            summary = "Delete ninja by ID",
            description = """
            Permanently deletes a ninja by its unique identifier.
            
            **Permissions:** Admin users only
            
            **Responses:**
            - `200`: Ninja deleted successfully
            - `404`: Ninja not found
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Ninja not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> deleteNinjas(@PathVariable UUID uuid) {
        NinjaDTO ninjaDTO = ninjaService.listNinjasById(uuid);

        if (ninjaDTO != null) {
            ninjaService.deleteNinjaById(uuid);
            return ResponseEntity.ok("Ninja deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja not found.");
        }
    }

    @PutMapping("/edit/{uuid}")
    @Operation(
            summary = "Update ninja by ID",
            description = """
            Updates an existing ninja's information by its unique identifier.
            Supports partial updates - only provided fields will be modified.
            
            **Permissions:** Ninja owner or Admin
            
            **Responses:**
            - `200`: Ninja updated successfully
            - `404`: Ninja not found
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja updated successfully"),
            @ApiResponse(responseCode = "404", description = "Ninja not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> editNinjas(@PathVariable UUID uuid, @RequestBody NinjaDTO ninjaUpdated) {
        NinjaDTO ninjaDTO = ninjaService.listNinjasById(uuid);

        if (ninjaDTO != null) {
            ninjaService.updateNinja(ninjaUpdated, uuid);
            return ResponseEntity.ok("Ninja updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja not found.");
        }
    }
}
