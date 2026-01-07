package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/missions")
@RestController
public class MissionsController {

    private MissionsService missionsService;

    public MissionsController(MissionsService missionsService) {
        this.missionsService = missionsService;
    }

    @GetMapping("/list")
    public List<MissionsModel> listMission(){
        return missionsService.listMissions();
    }

    @GetMapping("/list/{uuid}")
    public MissionsModel listMissionById(@PathVariable UUID uuid){
        return missionsService.getMissionById(uuid);
    }

    @PostMapping("/create")
    public MissionsModel createMission(@RequestBody MissionsModel mission) {
        return missionsService.createMission(mission);
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteMission(@PathVariable UUID uuid) {
        missionsService.deleteMission(uuid);
    }

    @PutMapping("/edit")
    public String editMission(){
        return "success";
    }

}
