package dev.wesleyjunior.RegisterNinjas.Ninjas;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
        .map(ninjaMapper::map)
        .collect(Collectors.toList());

    }

    public NinjaDTO listNinjasById(UUID uuid) {
        Optional<NinjaModel> ninja = ninjaRepository.findById(uuid);
        return ninja.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO createNinja(NinjaDTO ninjaDTO) {

        NinjaModel ninjaModel = ninjaMapper.map(ninjaDTO);
        ninjaModel = ninjaRepository.save(ninjaModel);

        return ninjaMapper.map(ninjaModel);
    }

    public void deleteNinjaById(UUID uuid) {
        ninjaRepository.deleteById(uuid);
    }

    public NinjaDTO updateNinja(NinjaDTO ninjaUpdated, UUID uuid) {
//        if (ninjaRepository.existsById(uuid)) {
//            ninjaUpdated.setId(uuid);
//            return ninjaRepository.save(ninjaUpdated);
//        }

        Optional<NinjaModel> ninja = ninjaRepository.findById(uuid);

        if (ninja.isPresent()) {
            NinjaModel ninjaModel = ninjaMapper.map(ninjaUpdated);
            ninjaModel.setId(uuid);
            NinjaModel ninjaModelUpdated = ninjaRepository.save(ninjaModel);

            return ninjaMapper.map(ninjaModelUpdated);
        }
        return null;
    }
}
