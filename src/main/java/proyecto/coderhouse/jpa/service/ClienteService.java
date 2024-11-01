package proyecto.coderhouse.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.coderhouse.jpa.entity.Cliente;
import proyecto.coderhouse.jpa.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getById(Integer id) {
        return clienteRepository.findById(id);
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}

