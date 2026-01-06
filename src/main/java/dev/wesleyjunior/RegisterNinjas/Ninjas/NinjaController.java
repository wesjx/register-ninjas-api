package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/list")
    public List<NinjaModel> listNinjas() {
        return ninjaService.listNinjas();
    }

    @GetMapping("/list/{uuid}")
    public NinjaModel listNinjasById(@PathVariable UUID uuid) {
        return ninjaService.listNinjasById(uuid);
    }

    @PostMapping("/create")
    public NinjaModel createNinja(@RequestBody NinjaModel ninja) {
        return ninjaService.createNinja(ninja);
    }

    @PutMapping("/edit")
    public String editNinjas(@RequestParam UUID uuid) {
        return "Edit Ninjas by id";
    }

    @DeleteMapping("/delete")
    public String deleteNinjas(@RequestParam UUID uuid) {
        return "Delete Ninjas by id";
    }

}
