package proyecto.sistema.inventario.Inventario.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyecto.sistema.inventario.Inventario.Model.Categoria;
import proyecto.sistema.inventario.Inventario.Repository.CategoriaRepository;

import java.util.List;

@Service
public class CategoriaServicesImp implements CategoriaServices {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> listarTodasCategorias() {
        return categoriaRepository.findAll();
    }
    @Override
    public Page<Categoria> listarCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable);
    }
    @Override
    public Categoria obtenerCategoriaPorId(int id) {
        return categoriaRepository.findById(id).orElseThrow();
    }
    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    @Override
    public Categoria actualizarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    @Override
    public void eliminarCategoriaPorId(int id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public long countCategorias() {
        return categoriaRepository.count();
    }

    @Override
    public Page<Categoria> buscarCategoriasPorNombre(String nombre, Pageable pageable) {
        return categoriaRepository.findByNombreContainingIgnoreCase(nombre, pageable);
    }
}
