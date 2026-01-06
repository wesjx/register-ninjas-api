package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/create")
    public String createNinja() {
        return "Test post";
    }

    @GetMapping("/search")
    public String searchNinjas(@RequestParam long id) {
        return "All Ninjas by id";
    }

    @PutMapping("/edit")
    public String editNinjas(@RequestParam long id) {
        return "Edit Ninjas by id";
    }

    @DeleteMapping("/delete")
    public String deleteNinjas(@RequestParam long id) {
        return "Delete Ninjas by id";
    }

}
