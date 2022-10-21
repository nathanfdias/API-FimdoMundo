package br.org.serratec.service;

// import java.util.List;
// import java.util.Optional;

// import javax.transaction.Transactional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import br.org.serratec.model.Categoria;
// import br.org.serratec.repository.CategoriaRepository;
	
	// @Service
	public class CategoriaService {

	    // @Autowired
	    // CategoriaRepository categoriaRepository;

	    // public List<Categoria> listar() {
	    //     return categoriaRepository.findAll();
	    // }

	    // public Optional<Categoria> findById(Long id) {
	    //     return categoriaRepository.findById(id);
	    // }

	    // @Transactional
	    // public Categoria PostCategoria(Categoria categoria) {
	    //     Optional<Categoria> categoriaTemp = categoriaRepository.findById(categoria.getIdCategoria());
	    //     if (categoriaTemp.isPresent()) {
	    //         return null;
	    //     }

	    //     categoriaRepository.save(categoria);
	    //     return categoria;
	    // }

	    // public Optional<Categoria> PutCategoria(Categoria categoria, Long id) {
	    //     Optional<Categoria> categoriaTemp = categoriaRepository.findById(id);
	    //     if (categoriaTemp.isPresent()) {
	    //         categoria.setIdCategoria(id);
	    //         categoria = categoriaRepository.save(categoria);
	    //         return Optional.of(categoria);
	    //     }
	    //     return null;
	    // }

	    // public Boolean Delete(Long id) {
	    //     Optional<Categoria> categoriaTemp = categoriaRepository.findById(id);
	    //     if (!categoriaTemp.isPresent()) {
	    //         return false;
	    //     }
	    //     categoriaRepository.deleteById(id);
	    //     return true;
	    // }
}

