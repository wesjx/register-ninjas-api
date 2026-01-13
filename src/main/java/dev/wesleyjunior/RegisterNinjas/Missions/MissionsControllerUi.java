package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/missions/ui")
public class MissionsControllerUi {

    private final MissionsService missionsService;

    public MissionsControllerUi(MissionsService missionsService) {
        this.missionsService = missionsService;
    }

    @GetMapping("/list")
    public String listMissions(Model model) {
        List<MissionsModel> missions = missionsService.listMissions();
        model.addAttribute("missions", missions);
        return "indexMissions";
    }

    @GetMapping("/list/{uuid}")
    public String missionDetails(@PathVariable UUID uuid, Model model) {
        MissionsModel mission = missionsService.getMissionById(uuid);

        if (mission == null) {
            return "redirect:/missions/ui/list";
        }

        model.addAttribute("mission", mission);
        return "missionDetails";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("mission", new MissionsModel());
        return "createMission";
    }

    @PostMapping("/create")
    public String createMission(@ModelAttribute MissionsModel mission) {
        missionsService.createMission(mission);
        return "redirect:/missions/ui/list";
    }

    @GetMapping("/delete/{uuid}")
    public String deleteMission(@PathVariable UUID uuid, Model model) {
        try {
            missionsService.deleteMission(uuid);
            model.addAttribute("message", "Mission deleted successfully.");
        } catch (RuntimeException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "redirect:/missions/ui/list";
    }
}
