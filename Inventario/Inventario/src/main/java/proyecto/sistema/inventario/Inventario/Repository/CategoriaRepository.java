package proyecto.sistema.inventario.Inventario.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.sistema.inventario.Inventario.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
