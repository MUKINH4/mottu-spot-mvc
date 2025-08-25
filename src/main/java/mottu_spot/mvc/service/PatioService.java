package mottu_spot.mvc.service;

import mottu_spot.mvc.model.Patio;
import mottu_spot.mvc.repository.PatioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatioService {

    private final PatioRepository patioRepository;

    public PatioService(PatioRepository patioRepository) {
        this.patioRepository = patioRepository;
    }

    public List<Patio> listarPatios() {
        return patioRepository.findAll();
    }
    public Patio encontrarPatio(Long id){
        return patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patio no encontrado"));
    }

    public Patio salvarPatio(Patio patio) {
        return patioRepository.save(patio);
    }

    public void deletePatio(Long id) {
        patioRepository.deleteById(id);
    }
}
