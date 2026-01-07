package dev.wesleyjunior.RegisterNinjas.Missions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MissionsService {

    private MissionsRepository missionsRepository;

    public MissionsService(MissionsRepository missionsRepository) {
        this.missionsRepository = missionsRepository;
    }

    public List<MissionsModel> listMissions() {
        return missionsRepository.findAll();
    }

    public MissionsModel getMissionById(UUID id) {
        return missionsRepository.findById(id).orElse(null);
    }

    public MissionsModel createMission(MissionsModel mission) {
        return missionsRepository.save(mission);
    }

    public void deleteMission(UUID uuid){
        missionsRepository.deleteById(uuid);
    }

}
