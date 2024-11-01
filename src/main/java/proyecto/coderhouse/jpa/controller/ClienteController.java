package proyecto.coderhouse.jpa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.coderhouse.jpa.entity.Cliente;
import proyecto.coderhouse.jpa.service.ClienteService;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")  // Ruta base para los clientes
public class ClienteController {

    @Autowired
    private ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Cliente>> getAll() {
        Iterable<Cliente> clientes = service.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Cliente>> getById(@PathVariable Integer id) {
        Optional<Cliente> cliente = service.getById(id);
        if (cliente.isPresent()) {

            return ResponseEntity.ok(cliente);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = service.save(cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = service.getById(id);
        if (clienteExistente.isPresent()) {
            cliente.setId(id);
            Cliente clienteActualizado = service.save(cliente);
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = service.getById(id);
        if (cliente.isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
