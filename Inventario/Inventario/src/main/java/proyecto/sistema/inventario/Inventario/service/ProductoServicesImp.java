package proyecto.sistema.inventario.Inventario.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.sistema.inventario.Inventario.Model.Producto;
import proyecto.sistema.inventario.Inventario.Repository.ProductoRepository;

import java.util.List;
@Service
public class ProductoServicesImp implements ProductoServices {
    @Autowired
    public ProductoRepository productoRepository;
    @Override
    public Page<Producto> listarProductos(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepository.findAll() ;
    }
    @Override
    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).get();
    }
    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    @Override
    public void eliminarProductoPorId(int id) {
        productoRepository.deleteById(id);
    }
    @Override
    public long countProductos() {
        return productoRepository.count();
    }

    @Override
    public Page<Producto> buscarProductoPorNombre(String nombre, Pageable pageable) {
        Page<Producto> productos = (Page<Producto>) productoRepository.findByNombreContainingIgnoreCase(nombre, pageable);
        return productos;
    }


}
