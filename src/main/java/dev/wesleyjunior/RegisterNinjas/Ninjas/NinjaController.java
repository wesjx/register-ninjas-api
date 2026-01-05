package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/")
    public String registerNinjas() {
        return "Test";
    };

    @PostMapping("/criar")
    public String criarNinja() {
        return "Test post";
    }

    @GetMapping("/all")
    public String allNinjas() {
        return "All Ninjas";
    }

    @GetMapping("/search_by_id")
    public String searchNinjas(@RequestParam long id) {
        return "All Ninjas by id";
    }

    @PutMapping("/edit_ninja")
    public String editNinjas(@RequestParam long id) {
        return "Edit Ninjas by id";
    }

    @DeleteMapping("/delete_ninja")
    public String deleteNinjas(@RequestParam long id) {
        return "Delete Ninjas by id";
    }

}
