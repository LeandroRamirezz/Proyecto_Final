package proyecto.sistema.inventario.Inventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.sistema.inventario.Inventario.Model.Proveedor;
import proyecto.sistema.inventario.Inventario.Repository.ProveedorRepository;

import java.util.List;
@Service
public class ProveedorServicesImp implements ProveedorServices {
    @Autowired
    ProveedorRepository proveedorRepository;
    @Override
    public Page<Proveedor> listarProveedores(Pageable pageable) {
        return proveedorRepository.findAll(pageable);
    }
    @Override
    public List<Proveedor> listarTodosProveedores() {
        return proveedorRepository.findAll();
    }
    @Override
    public Proveedor obtenerProveedorPorId(int id) {
        return proveedorRepository.findById(id).orElseThrow();
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminarProveedorPorId(int id) {
    proveedorRepository.deleteById(id);
    }

    @Override
    public long countProveedor() {
        return proveedorRepository.count();
    }

    @Override
    public Page<Proveedor> buscarProveedorPorNombre(String nombre, Pageable pageable) {
        return proveedorRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }
}
