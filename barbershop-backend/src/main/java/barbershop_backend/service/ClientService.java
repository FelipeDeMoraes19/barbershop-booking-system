package barbershop_backend.service;

import barbershop_backend.dto.ClientDTO;
import barbershop_backend.exception.ResourceNotFoundException;
import barbershop_backend.mapper.ClientMapper;
import barbershop_backend.model.Client;
import barbershop_backend.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        if (clientRepository.existsByEmail(clientDTO.email())) {
            throw new EmailAlreadyExistsException(clientDTO.email());
        }
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDto(clientRepository.save(client));
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        
        clientMapper.updateClientFromDto(clientDTO, existingClient);
        return clientMapper.toDto(clientRepository.save(existingClient));
    }

    @Transactional
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
}