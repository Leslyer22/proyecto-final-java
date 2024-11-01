package proyecto.coderhouse.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.coderhouse.jpa.entity.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
