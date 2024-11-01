package proyecto.coderhouse.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import proyecto.coderhouse.jpa.entity.Comprobante;
import proyecto.coderhouse.jpa.repository.ComprobanteRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String fechaApiUrl = "https://www.timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";

    public Comprobante crearComprobante(Comprobante comprobante) {
        // Validar si el stock en la venta es suficiente
        if (comprobante.getVenta().getStock() <= 0) {
            throw new IllegalArgumentException("Stock insuficiente para la venta");
        }

        // Obtener la fecha actual desde la API
        LocalDateTime fecha = obtenerFechaActual();
        comprobante.setFecha(fecha);

        // Guardar el comprobante en la base de datos
        return comprobanteRepository.save(comprobante);
    }


    private LocalDateTime obtenerFechaActual() {
        String fechaString = restTemplate.getForObject(fechaApiUrl, String.class);
        return LocalDateTime.parse(fechaString);  // Ajusta el formato si es necesario
    }


    public Comprobante obtenerComprobantePorId(int id) {
        return comprobanteRepository.findById(id).orElse(null);
    }

    public Comprobante actualizarComprobante(int id, Comprobante detallesComprobante) {
        Comprobante comprobanteExistente = obtenerComprobantePorId(id);
        if (comprobanteExistente != null) {
            comprobanteExistente.setVenta(detallesComprobante.getVenta());
            // Actualizar otros campos si es necesario
            return comprobanteRepository.save(comprobanteExistente);
        } else {
            throw new RuntimeException("Comprobante no encontrado");
        }
    }

    public void eliminarComprobante(int id) {
        comprobanteRepository.deleteById(id);
    }

    public List<Comprobante> obtenerTodosLosComprobantes() {
    }
}
