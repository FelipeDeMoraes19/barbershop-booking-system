package barbershop_backend.service;

import barbershop_backend.dto.AppointmentDTO;
import barbershop_backend.exception.ConflictException;
import barbershop_backend.exception.ResourceNotFoundException;
import barbershop_backend.mapper.AppointmentMapper;
import barbershop_backend.model.Appointment;
import barbershop_backend.model.AppointmentStatus;
import barbershop_backend.model.BarberServiceEntity;
import barbershop_backend.repository.AppointmentRepository;
import barbershop_backend.repository.BarberServiceRepository;
import barbershop_backend.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import barbershop_backend.model.Client;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final BarberServiceRepository barberServiceRepository;
    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) {
        Client client = clientRepository.findById(appointmentDTO.clientId())
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        BarberServiceEntity service = barberServiceRepository.findById(appointmentDTO.serviceId())
            .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

        if (appointmentDTO.startTime().isBefore(LocalDateTime.now())) {
            throw new ConflictException("Não é possível agendar para um horário passado.");
        }

        LocalDateTime endTime = appointmentDTO.startTime().plusMinutes(service.getDurationMinutes());

        if (appointmentRepository.existsByClientIdAndStartTimeBetween(
                appointmentDTO.clientId(),
                appointmentDTO.startTime().minusMinutes(30),
                endTime.plusMinutes(30))) {
            throw new ConflictException("Conflito de horário para este cliente");
        }

        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        appointment.setClient(client);
        appointment.setService(service);
        appointment.setEndTime(endTime);
        appointment.setStatus(AppointmentStatus.AGENDADO);

        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .toList();
    }

    public AppointmentDTO getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));
    }

    @Transactional
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado"));

        if (!clientRepository.existsById(appointmentDTO.clientId())) {
            throw new ResourceNotFoundException("Cliente não encontrado");
        }

        BarberServiceEntity serviceEntity = barberServiceRepository.findById(appointmentDTO.serviceId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

        if (appointmentDTO.startTime().isBefore(LocalDateTime.now())) {
            throw new ConflictException("Não é possível agendar para um horário passado.");
        }

        LocalDateTime newEndTime = appointmentDTO.startTime().plusMinutes(serviceEntity.getDurationMinutes());

        if (appointmentRepository.existsByClientIdAndStartTimeBetween(
                appointmentDTO.clientId(),
                appointmentDTO.startTime().minusMinutes(30),
                newEndTime.plusMinutes(30))) {
            throw new ConflictException("Conflito de horário para este cliente");
        }

        existingAppointment.setClient(clientRepository.getReferenceById(appointmentDTO.clientId()));
        existingAppointment.setService(serviceEntity);
        existingAppointment.setStartTime(appointmentDTO.startTime());
        existingAppointment.setEndTime(newEndTime);

        if (appointmentDTO.status() != null) {
            existingAppointment.setStatus(appointmentDTO.status());
        }

        Appointment updated = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDto(updated);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento não encontrado");
        }
        appointmentRepository.deleteById(id);
    }
}
