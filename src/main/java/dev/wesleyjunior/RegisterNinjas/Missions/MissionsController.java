package dev.wesleyjunior.RegisterNinjas.Missions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Missions", description = "Gerenciamento completo de missões dos ninjas")
@RequestMapping("/missions")
@RestController
public class MissionsController {

    private final MissionsService missionsService;

    public MissionsController(MissionsService missionsService) {
        this.missionsService = missionsService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all missions",
            description = """
            Returns a complete list of all missions registered in the system.
            
            **Permissions:** Public endpoint
            
            **Response:**
            - `200`: Success - Returns List<MissionsModel>
            
            **Example:**
            ```json
            [
              {
                "uuid": "123e4567-e89b-12d3-a456-426614174000",
                "title": "Resgate do Hokage",
                "difficulty": "HARD"
              }
            ]
            ```
            """
    )
    public ResponseEntity<List<MissionsModel>> listMission() {
        List<MissionsModel> missions = missionsService.listMissions();
        return ResponseEntity.ok(missions);
    }

    @GetMapping("/list/{uuid}")
    @Operation(
            summary = "Get mission by ID",
            description = """
            Retrieves a specific mission by its unique identifier (UUID).
            
            **Permissions:** Public endpoint
            
            **Responses:**
            - `200`: Mission found successfully
            - `404`: Mission not found
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mission found successfully"),
            @ApiResponse(responseCode = "404", description = "Mission not found")
    })
    public ResponseEntity<MissionsModel> listMissionById(@PathVariable UUID uuid) {
        MissionsModel mission = missionsService.getMissionById(uuid);
        if (mission != null) {
            return ResponseEntity.ok(mission);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create new mission",
            description = """
            Creates a new mission in the system.
            
            **Permissions:** Authenticated users
            
            **Request Body:** MissionsModel with title, difficulty and other required fields
            
            **Responses:**
            - `201`: Mission created successfully
            - `400`: Invalid request data
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mission created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MissionsModel> createMission(@RequestBody MissionsModel mission) {
        MissionsModel createdMission = missionsService.createMission(mission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
    }

    @DeleteMapping("/delete/{uuid}")
    @Operation(
            summary = "Delete mission by ID",
            description = """
            Permanently deletes a mission by its unique identifier.
            
            **Permissions:** Admin users only
            
            **Responses:**
            - `204`: Mission deleted successfully
            - `404`: Mission not found
            - `500`: Internal server error
            """
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mission deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Mission not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteMission(@PathVariable UUID uuid) {
        missionsService.deleteMission(uuid);
        return ResponseEntity.noContent().build();
    }

}
