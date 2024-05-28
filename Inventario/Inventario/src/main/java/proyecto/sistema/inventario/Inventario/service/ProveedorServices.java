package proyecto.sistema.inventario.Inventario.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proyecto.sistema.inventario.Inventario.Model.Proveedor;

import java.util.List;

public interface ProveedorServices {
    public Page<Proveedor> listarProveedores(Pageable pageable);
    public List<Proveedor> listarTodosProveedores();
    public Proveedor obtenerProveedorPorId(int id);
    public Proveedor guardarProveedor(Proveedor proveedor);
    public Proveedor actualizarProveedor(Proveedor proveedor);
    public void eliminarProveedorPorId(int id);
    public long countProveedor();

    public Page<Proveedor> buscarProveedorPorNombre(String nombre, Pageable pageable);
}
