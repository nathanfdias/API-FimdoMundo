package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.CategoriaDTO;
import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listar() {

        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = new ArrayList<>();

        for (Categoria categoria : categorias) {
            categoriasDTO.add(new CategoriaDTO(categoria));
        }

        return categoriasDTO;
    }
    
    public CategoriaDTO buscar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (!categoria.isPresent()) {
            return null;
        }

        return new CategoriaDTO(categoria.get());
    }
    
    public CategoriaDTO inserir(Categoria categoria) {

        Categoria cat = new Categoria();
        cat.setNome(categoria.getNome());
        cat.setDescricao(categoria.getDescricao());
        categoriaRepository.save(cat);

        return new CategoriaDTO(cat);
    }

    
    public Boolean delete(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            categoriaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
