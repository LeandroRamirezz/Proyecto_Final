package proyecto.sistema.inventario.Inventario.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import proyecto.sistema.inventario.Inventario.Model.Categoria;

import java.util.List;

public interface CategoriaServices {
    public Page<Categoria> listarCategorias(Pageable pageable);
    public List<Categoria> listarTodasCategorias();
    public Categoria obtenerCategoriaPorId(int id);
    public Categoria guardarCategoria(Categoria categoria);
    public Categoria actualizarCategoria(Categoria categoria);
    public void eliminarCategoriaPorId(int id);
    public long countCategorias();
    public Page<Categoria> buscarCategoriasPorNombre(String nombre, Pageable pageable);
}
