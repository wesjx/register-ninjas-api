package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    public List<NinjaModel> listNinjas(){
        return ninjaRepository.findAll();
    }

    public NinjaModel listNinjasById(UUID uuid){
        Optional<NinjaModel> ninja = ninjaRepository.findById(uuid);
        return ninja.orElse(null);
    }

    public NinjaModel createNinja(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

}
