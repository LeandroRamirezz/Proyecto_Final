package proyecto.sistema.inventario.Inventario.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import proyecto.sistema.inventario.Inventario.Model.Usuario;

public interface UsuarioServices extends UserDetailsService {
    public Usuario save(Usuario usuario);
}
