package proyecto.sistema.inventario.Inventario.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.sistema.inventario.Inventario.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
