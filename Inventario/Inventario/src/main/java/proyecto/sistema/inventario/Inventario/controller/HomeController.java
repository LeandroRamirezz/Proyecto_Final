package proyecto.sistema.inventario.Inventario.controller;

// Importamos las clases necesarias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.sistema.inventario.Inventario.service.CategoriaServices;
import proyecto.sistema.inventario.Inventario.service.ProductoServices;
import proyecto.sistema.inventario.Inventario.service.ProveedorServices;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    CategoriaServices categoriaServices;
    @Autowired
    ProductoServices productoServices;
    @Autowired
    ProveedorServices proveedorServices;

    @GetMapping("/home")
    public String viewHome(Model model) {
        model.addAttribute("countProductos", productoServices.countProductos());
        model.addAttribute("countCategorias", categoriaServices.countCategorias());
        model.addAttribute("countProveedores", proveedorServices.countProveedor());
        return "home";
    }
}
