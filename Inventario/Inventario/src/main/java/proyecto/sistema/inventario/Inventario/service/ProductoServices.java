package proyecto.sistema.inventario.Inventario.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proyecto.sistema.inventario.Inventario.Model.Producto;

import java.util.List;

public interface ProductoServices {
    public Page<Producto> listarProductos(Pageable pageable);
    public List<Producto> listarTodosProductos();
    public Producto obtenerProductoPorId(int id);
    public Producto guardarProducto(Producto producto);
    public Producto actualizarProducto(Producto producto);
    public void eliminarProductoPorId(int id);
    public long countProductos();

    public Page<Producto> buscarProductoPorNombre(String nombre, Pageable pageable);
}
