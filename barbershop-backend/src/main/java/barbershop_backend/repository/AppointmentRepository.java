package barbershop_backend.repository;

import barbershop_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
    @Query("SELECT a FROM Appointment a WHERE a.startTime BETWEEN :start AND :end")
    List<Appointment> findBetweenDates(LocalDateTime start, LocalDateTime end);
    
    boolean existsByClientIdAndStartTimeBetween(Long clientId, LocalDateTime start, LocalDateTime end);
}