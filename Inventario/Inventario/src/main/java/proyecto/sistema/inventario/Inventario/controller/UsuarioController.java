package proyecto.sistema.inventario.Inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.sistema.inventario.Inventario.Model.Usuario;
import proyecto.sistema.inventario.Inventario.service.UsuarioServices;


@Controller

@RequestMapping("/registro")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;


    @ModelAttribute("usuario")
    public Usuario retornarNuevoUsuario() {
        return new Usuario();
    }

    @GetMapping
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute Usuario usuario) {

        usuarioServices.save(usuario);
        return "redirect:/registro?success";
    }
}