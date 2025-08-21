package mottu_spot.mvc.repository;

import mottu_spot.mvc.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoRepository extends JpaRepository<Moto, Long> {
}
