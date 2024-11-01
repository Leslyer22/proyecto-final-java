package proyecto.coderhouse.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.coderhouse.jpa.entity.Comprobante;
import proyecto.coderhouse.jpa.service.ComprobanteService;

import java.util.List;

@RestController
@RequestMapping("/comprobante")
public class ComprobanteController {
    @Autowired
    private ComprobanteService comprobanteService;

    @PostMapping
    public ResponseEntity<?> crearComprobante(@RequestBody Comprobante comprobante) {
        try {
            Comprobante nuevoComprobante = comprobanteService.crearComprobante(comprobante);
            return ResponseEntity.ok(nuevoComprobante);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el comprobante");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprobante> obtenerComprobante(@PathVariable int id) {
        Comprobante comprobante = comprobanteService.obtenerComprobantePorId(id);
        return comprobante != null ? ResponseEntity.ok(comprobante) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Comprobante> obtenerTodosLosComprobantes() {
        return comprobanteService.obtenerTodosLosComprobantes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComprobante(@PathVariable int id, @RequestBody Comprobante comprobanteDetalles) {
        try {
            Comprobante comprobanteActualizado = comprobanteService.actualizarComprobante(id, comprobanteDetalles);
            return ResponseEntity.ok(comprobanteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarComprobante(@PathVariable int id) {
        try {
            comprobanteService.eliminarComprobante(id);
            return ResponseEntity.ok("Comprobante eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el comprobante");
        }
    }
}