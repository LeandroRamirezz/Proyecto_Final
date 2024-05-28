package proyecto.sistema.inventario.Inventario.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.sistema.inventario.Inventario.Model.Categoria;
import proyecto.sistema.inventario.Inventario.service.CategoriaServices;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaServices categoriaServices;

    @GetMapping("/categorias")
    public String vistaListaCategorias(@RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Categoria> categorias = categoriaServices.listarCategorias(pageable);
        model.addAttribute("categorias", categorias);
        model.addAttribute("currentPage", categorias.getNumber());
        model.addAttribute("totalPages", categorias.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-categorias";
    }

    @GetMapping("/categorias/crear")
    public String mostrarFormularioCrearCategoria(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "crear-categoria";
    }

    @PostMapping("/categorias")
    public String crearCategoria(@ModelAttribute Categoria categoria) {
        categoriaServices.guardarCategoria(categoria);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/editar/{id}")
    public String mostrarFormEditarCategoria(@PathVariable int id, Model model) {
        // Obtenemos la categoría por su ID y la agregamos a la vista
        model.addAttribute("categoria", categoriaServices.obtenerCategoriaPorId(id));
        return "editar-categoria";
    }

    @PostMapping("/categorias/{id}")
    public String actualizarCategoria(@PathVariable int id, @ModelAttribute Categoria categoria) {
        Categoria categoria1 = categoriaServices.obtenerCategoriaPorId(id);
        categoria1.setId(id);
        categoria1.setNombre(categoria.getNombre());
        categoriaServices.actualizarCategoria(categoria1);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/{id}")
    public String eliminarCategoria(@PathVariable int id) {
        categoriaServices.eliminarCategoriaPorId(id);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/buscar")
    public String buscarCategoriasPorNombre(@RequestParam(defaultValue = "0") int page, @RequestParam String nombre, Model model, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Categoria> categorias = categoriaServices.buscarCategoriasPorNombre(nombre, pageable);
        model.addAttribute("categorias", categorias);
        model.addAttribute("currentPage", categorias.getNumber());
        model.addAttribute("totalPages", categorias.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-categorias";
    }
}