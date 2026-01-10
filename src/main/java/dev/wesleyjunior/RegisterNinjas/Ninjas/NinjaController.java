package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<NinjaDTO>> listNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/list/{uuid}")
    public ResponseEntity<?> listNinjasById(@PathVariable UUID uuid) {
        NinjaDTO ninjaDTO = ninjaService.listNinjasById(uuid);
        if (ninjaDTO != null) {
            return ResponseEntity.ok(ninjaDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja id:" + uuid + " not found.");
    }


    @PostMapping("/create")
    public ResponseEntity<String> createNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.createNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja created successfully." + "Ninja's name: " + ninjaDTO.getName());
    }

    @DeleteMapping("/delete/{uuid}")
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
    public ResponseEntity<String> editNinjas(@PathVariable UUID uuid, @RequestBody NinjaDTO ninjaUpdated) {
        NinjaDTO ninjaDTO = ninjaService.listNinjasById(uuid);

        if (ninjaDTO != null) {
            ninjaService.updateNinja(ninjaUpdated, uuid);
            return ResponseEntity.ok("Ninja updated successfully. Ninja updated: " + ninjaUpdated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja not found.");
        }

    }

}
