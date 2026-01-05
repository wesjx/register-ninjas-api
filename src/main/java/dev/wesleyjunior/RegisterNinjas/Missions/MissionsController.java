package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.web.bind.annotation.*;

@RequestMapping("missions")
@RestController
public class MissionsController {

    @GetMapping("/list")
    public String listMission(){
        return "success";
    }

    @PostMapping("/create")
    public String createMission(){
        return "success";
    }

    @PutMapping("/edit")
    public String editMission(){
        return "success";
    }

    @DeleteMapping("/delete")
    public String deleteMission(){
        return "success";
    }

    
}
