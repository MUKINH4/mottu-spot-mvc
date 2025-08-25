package mottu_spot.mvc.service;

import mottu_spot.mvc.model.Moto;
import mottu_spot.mvc.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    public List<Moto> listarMotos() {
        return motoRepository.findAll();
    }

    public List<Moto> encontrarMotoPorPatio(Long patioId) {
        return motoRepository.findByPatioId(patioId);
    }

    public Moto criarMoto(Moto moto){
        return motoRepository.save(moto);
    }

}
