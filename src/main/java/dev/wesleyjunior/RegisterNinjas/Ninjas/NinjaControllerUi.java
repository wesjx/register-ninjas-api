package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjaService ninjaService;

    public NinjaControllerUi(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/list")
    public String listNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjaService.listNinjas();
        model.addAttribute("ninjas", ninjas);
        return "index";
    }

    @GetMapping("/list/{uuid}")
    public String ninjaDetails(@PathVariable UUID uuid, Model model) {
        NinjaDTO ninja = ninjaService.listNinjasById(uuid);

        if (ninja == null) {
            return "redirect:/ninjas/ui/list";
        }

        model.addAttribute("ninja", ninja);
        return "ninjaDetails";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "createNinja";
    }

    @PostMapping("/create")
    public String createNinja(@ModelAttribute NinjaDTO ninja) {
        ninjaService.createNinja(ninja);
        return "redirect:/ninjas/ui/list";
    }

    @GetMapping("/edit/{uuid}")
    public String editForm(@PathVariable UUID uuid, Model model) {
        NinjaDTO ninja = ninjaService.listNinjasById(uuid);

        if (ninja == null) {
            return "redirect:/ninjas/ui/list";
        }

        model.addAttribute("ninja", ninja);
        return "editNinja";
    }

    @PostMapping("/edit/{uuid}")
    public String updateNinja(@PathVariable UUID uuid, @ModelAttribute NinjaDTO ninjaUpdated) {
        ninjaService.updateNinja(ninjaUpdated, uuid);
        return "redirect:/ninjas/ui/list";
    }

    @GetMapping("/delete/{uuid}")
    public String deleteNinja(@PathVariable UUID uuid) {
        ninjaService.deleteNinjaById(uuid);
        return "redirect:/ninjas/ui/list";
    }
}
