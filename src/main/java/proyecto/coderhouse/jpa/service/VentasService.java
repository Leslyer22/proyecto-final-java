package proyecto.coderhouse.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.coderhouse.jpa.entity.Ventas;
import proyecto.coderhouse.jpa.repository.VentasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService {

    @Autowired
    private VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    public Ventas save(Ventas venta) {
        return ventasRepository.save(venta);
    }

    public List<Ventas> getVentas() {
        return ventasRepository.findAll();
    }

    public Optional<Ventas> getById(Integer id) {
        return ventasRepository.findById(id);
    }

    public void deleteById(Integer id) {
        ventasRepository.deleteById(id);
    }
}

