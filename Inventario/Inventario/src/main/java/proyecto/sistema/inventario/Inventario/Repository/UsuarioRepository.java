package proyecto.sistema.inventario.Inventario.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.sistema.inventario.Inventario.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByEmail(String email);
}
