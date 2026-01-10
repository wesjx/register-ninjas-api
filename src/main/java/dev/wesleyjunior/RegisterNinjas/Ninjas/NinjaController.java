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
    public List<NinjaDTO> listNinjas() {
        return ninjaService.listNinjas();
    }

    @GetMapping("/list/{uuid}")
    public NinjaDTO listNinjasById(@PathVariable UUID uuid) {
        return ninjaService.listNinjasById(uuid);
    }

    @PostMapping("/create")
    public NinjaDTO createNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.createNinja(ninja);
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteNinjas(@PathVariable UUID uuid) {
        ninjaService.deleteNinjaById(uuid);
    }

    @PutMapping("/edit/{uuid}")
    public NinjaDTO editNinjas(@PathVariable UUID uuid, @RequestBody NinjaDTO ninjaUpdated) {

        return ninjaService.updateNinja(ninjaUpdated, uuid);

    }

}
