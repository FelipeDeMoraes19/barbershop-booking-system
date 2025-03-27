package barbershop_backend.repository;

import barbershop_backend.model.BarberServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberServiceRepository extends JpaRepository<BarberServiceEntity, Long> {
    boolean existsByName(String name);
}