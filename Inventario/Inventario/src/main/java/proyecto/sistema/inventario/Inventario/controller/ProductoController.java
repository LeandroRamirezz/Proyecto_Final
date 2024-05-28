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
import proyecto.sistema.inventario.Inventario.Model.Producto;
import proyecto.sistema.inventario.Inventario.Model.Proveedor;
import proyecto.sistema.inventario.Inventario.service.CategoriaServices;
import proyecto.sistema.inventario.Inventario.service.ProductoServices;
import proyecto.sistema.inventario.Inventario.service.ProveedorServices;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoServices productoServices;
    @Autowired
    private CategoriaServices categoriaServices;
    @Autowired
    private ProveedorServices proveedorServices;

    @GetMapping("/productos")
    public String vistaListaProductos(@RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Producto> productos = productoServices.listarProductos(pageable);
        model.addAttribute("productos", productos);
        model.addAttribute("currentPage", productos.getNumber());
        model.addAttribute("totalPages", productos.getTotalPages());
        model.addAttribute("url", request.getRequestURI());
        return "listado-productos";
    }

    @GetMapping("/productos/crear")
    public String mostrarFormularioCrearProducto(Model model) {
        List<Categoria> categorias = categoriaServices.listarTodasCategorias();
        model.addAttribute("categorias", categorias);
        List<Proveedor> proveedores = proveedorServices.listarTodosProveedores();
        model.addAttribute("proveedores", proveedores);
        model.addAttribute("inventario", new Producto());
        return "crear-producto";
    }

        @PostMapping("/productos")
        public String crearProducto(@RequestParam String nombre,
                                    @RequestParam String descripcion,
                                    @RequestParam Float stock,
                                    @RequestParam Float precioCompra,
                                    @RequestParam Float precioVenta,
                                    @RequestParam Integer categoriaId,
                                    @RequestParam Integer proveedorId) {
            Categoria categoria = categoriaServices.obtenerCategoriaPorId(categoriaId);
            Proveedor proveedor = proveedorServices.obtenerProveedorPorId(proveedorId);

            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setStock(stock);
            producto.setDescripcion(descripcion);
            producto.setPrecioCompra(precioCompra);
            producto.setPrecioVenta(precioVenta);
            producto.setCategoria(categoria);
            producto.setProveedor(proveedor);

            productoServices.guardarProducto(producto);

            return "redirect:/productos";
        }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormEditarProducto(@PathVariable int id, Model model) {
        model.addAttribute("producto", productoServices.obtenerProductoPorId(id));
        model.addAttribute("categorias", categoriaServices.listarTodasCategorias());
        model.addAttribute("proveedores", proveedorServices.listarTodosProveedores());
        return "editar-producto";
    }

    @PostMapping("/productos/{id}")
    public String actuaizarProducto(@PathVariable int id, @ModelAttribute("inventario") Producto producto, Model model) {
        Producto producto1 = productoServices.obtenerProductoPorId(id);
        producto1.setId(id);
        producto1.setNombre(producto.getNombre());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setStock(producto.getStock());
        producto1.setPrecioCompra(producto.getPrecioCompra());
        producto1.setPrecioVenta(producto.getPrecioVenta());
        producto1.setCategoria(producto.getCategoria());
        producto1.setProveedor(producto.getProveedor());

        productoServices.actualizarProducto(producto1);
        return "redirect:/productos";
    }

    @GetMapping("/productos/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoServices.eliminarProductoPorId(id);
        return "redirect:/productos";
    }

    @GetMapping("/productos/buscar")
    public String buscarProductoPorNombre(
            @RequestParam String nombre,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {
        int pageSize = 6; // Tamaño de página
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Producto> productos = (Page<Producto>) productoServices.buscarProductoPorNombre(nombre, pageable);

        model.addAttribute("productos", productos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productos.getTotalPages());
        model.addAttribute("url", "/productos/buscar?nombre=" + nombre); // URL de búsqueda

        return "listado-productos";
    }
}