package proyecto.coderhouse.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.coderhouse.jpa.entity.Ventas;

public interface VentasRepository extends JpaRepository<Ventas, Integer> {
}

