package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NinjaService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaModel> listNinjas() {
        return ninjaRepository.findAll();
    }

    public NinjaModel listNinjasById(UUID uuid) {
        Optional<NinjaModel> ninja = ninjaRepository.findById(uuid);
        return ninja.orElse(null);
    }

    public NinjaDTO createNinja(NinjaDTO ninjaDTO) {

        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        ninjaModel = ninjaRepository.save(ninjaModel);

        return ninjaMapper.map(ninjaModel);
    }

    public void deleteNinjaById(UUID uuid) {
        ninjaRepository.deleteById(uuid);
    }

    public NinjaModel updateNinja(NinjaModel ninjaUpdated, UUID uuid) {
        if (ninjaRepository.existsById(uuid)) {
            ninjaUpdated.setId(uuid);
            return ninjaRepository.save(ninjaUpdated);
        }

        return null;
    }
}
