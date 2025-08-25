package mottu_spot.mvc.repository;

import mottu_spot.mvc.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Long> {

    List<Moto> findByPatioId(Long id);

}
