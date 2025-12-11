package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class MissionsController {

    @GetMapping("/missions")
    public String getMissions() {
        
        return "teste";
    }
    
    
}
