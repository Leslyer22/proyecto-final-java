package proyecto.coderhouse.jpa.service;

import proyecto.coderhouse.jpa.repository.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.coderhouse.jpa.entity.Productos;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosService {

    @Autowired
    private ProductosRepository productosRepository;

    public ProductosService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    public Productos save(Productos producto) {
        return productosRepository.save(producto);
    }

    public List<Productos> getProductos() {
        return productosRepository.findAll();
    }

    public Optional<Productos> getById(Integer id) {
        return productosRepository.findById(id);
    }

    public void deleteById(Integer id) {
        productosRepository.deleteById(id);
    }
}
