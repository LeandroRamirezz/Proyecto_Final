package proyecto.sistema.inventario.Inventario.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.sistema.inventario.Inventario.Model.Proveedor;
import proyecto.sistema.inventario.Inventario.service.ProveedorServices;

@Controller
public class ProveedorController {
    @Autowired
    ProveedorServices proveedorServices;

    @GetMapping("/proveedores")
    public String vistaListaProveedores(@RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Proveedor> proveedores = proveedorServices.listarProveedores(pageable);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("currentPage", proveedores.getNumber());
        model.addAttribute("totalPages", proveedores.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-proveedores";
    }

    @GetMapping("/proveedores/crear")
    public String mostrarFormularioCrearProveedores(Model model) {
        Proveedor proveedor = new Proveedor();
        model.addAttribute("proveedor", proveedor);
        return "crear-proveedor";
    }

    @PostMapping("/proveedores")
    public String crearProveedores(@ModelAttribute Proveedor proveedor) {
        proveedorServices.guardarProveedor(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/editar/{id}")
    public String mostrarFormEditarProveedor(@PathVariable int id, Model model) {
        model.addAttribute("proveedor", proveedorServices.obtenerProveedorPorId(id));
        return "editar-proveedor";
    }

    @PostMapping("/proveedor/{id}")
    public String actualizarProveedor(@PathVariable int id, @ModelAttribute Proveedor proveedor) {
        Proveedor proveedor1 = proveedorServices.obtenerProveedorPorId(id);
        proveedor1.setId(id);
        proveedor1.setNombre(proveedor.getNombre());
        proveedor1.setTelefono(proveedor.getTelefono());
        proveedorServices.actualizarProveedor(proveedor1);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/{id}")
    public String eliminarProveedor(@PathVariable int id) {
        // Eliminamos el proveedor por su ID
        proveedorServices.eliminarProveedorPorId(id);
        return "redirect:/proveedores";
    }

    @GetMapping("/proveedores/buscar")
    public String buscarProveedor(@RequestParam String nombre, @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Proveedor> proveedores = proveedorServices.buscarProveedorPorNombre(nombre, pageable);
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("currentPage", proveedores.getNumber());
        model.addAttribute("totalPages", proveedores.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-proveedores";
    }
}