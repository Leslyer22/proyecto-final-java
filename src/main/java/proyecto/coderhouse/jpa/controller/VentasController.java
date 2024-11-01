package proyecto.coderhouse.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.coderhouse.jpa.entity.Ventas;
import proyecto.coderhouse.jpa.service.VentasService;

import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")  // Ruta base para las ventas
public class VentasController {

    @Autowired
    private VentasService service;

    public VentasController(VentasService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Ventas>> getAll() {
        Iterable<Ventas> ventas = service.getVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Ventas>> getById(@PathVariable Integer id) {
        Optional<Ventas> venta = service.getById(id);
        if (venta.isPresent()) {
            // Si la venta existe
            return ResponseEntity.ok(venta);
        } else {
            // Si la venta no existe
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ventas> create(@RequestBody Ventas venta) {
        try {
            Ventas nuevaVenta = service.save(venta);
            return ResponseEntity.ok(nuevaVenta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Ventas> update(@PathVariable Integer id, @RequestBody Ventas ventaActualizada) {
        Optional<Ventas> ventaExistente = service.getById(id);
        if (ventaExistente.isPresent()) {
            Ventas venta = ventaExistente.get();
            venta.setCliente_id(ventaActualizada.getCliente_id());
            venta.setProducto_id(ventaActualizada.getProducto_id());
            venta.setTotal(ventaActualizada.getTotal());
            Ventas ventaGuardada = service.save(venta);
            return ResponseEntity.ok(ventaGuardada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Ventas> venta = service.getById(id);
        if (venta.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

