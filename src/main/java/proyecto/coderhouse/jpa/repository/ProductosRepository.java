package proyecto.coderhouse.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.coderhouse.jpa.entity.Productos;

public interface ProductosRepository  extends JpaRepository<Productos, Integer> {
}
