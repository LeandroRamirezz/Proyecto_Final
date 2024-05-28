package proyecto.sistema.inventario.Inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import proyecto.sistema.inventario.Inventario.Model.Rol;
import proyecto.sistema.inventario.Inventario.Model.Usuario;
import proyecto.sistema.inventario.Inventario.Repository.UsuarioRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UsuarioServicesImp implements UsuarioServices {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario save(Usuario usuario) {
        Usuario usuario1 = new Usuario(usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                passwordEncoder.encode(usuario.getPassword()),
                Arrays.asList(new Rol("ROLE_USER")));
        return usuarioRepository.save(usuario1);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new User(usuario.getEmail(), usuario.getPassword(), mapRolesToAuthorities(usuario.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles){
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
    }
}
