package proyecto.sistema.inventario.Inventario.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.sistema.inventario.Inventario.Model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    Page<Proveedor> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
