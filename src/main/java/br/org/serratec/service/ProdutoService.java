package br.org.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.dto.ProdutoInserirDTO;
import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.CategoriaRepository;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<ProdutoDTO> listar() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTO = new ArrayList<>();

        for (Produto produto : produtos) {
            produtoDTO.add(inserirUriDaImagem(produto));
        }

        return produtoDTO;
    }
    
    
    public ProdutoDTO buscar(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (!produto.isPresent()) {
            return null;
        }

        return inserirUriDaImagem(produto.get());
    }

    public Produto buscarFoto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            return null;
        }

        return produto.get();
    }

    public ProdutoDTO inserir(ProdutoInserirDTO produtoDTO, MultipartFile file) throws IOException {

        Optional<Categoria> cat = categoriaRepository.findById(produtoDTO.getCategoria().getId());

        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
        produto.setDataCadastro(produtoDTO.getDataCadastro());
        produto.setValorUnitario(produtoDTO.getValorUnitario());
        produto.setCategoria(cat.get());
        produto.setImagem(file.getBytes());

        produto = produtoRepository.save(produto);

        return inserirUriDaImagem(produto);
        
    }
    
    public ProdutoDTO inserirUriDaImagem(Produto produto){
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/foto/{id}")
                .buildAndExpand(produto.getId()).toUri();

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setDataCadastro(produto.getDataCadastro());
        dto.setValorUnitario(produto.getValorUnitario());
        dto.setCategoria(produto.getCategoria());
        dto.setUri(uri.toString());

        return dto;
    }

    public ProdutoDTO atualizar(Long id, ProdutoInserirDTO dto, MultipartFile file) throws IOException {

        dto.setId(id);
        Optional<Categoria> cat = categoriaRepository.findById(dto.getCategoria().getId());

        Produto produto = new Produto();

        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setCategoria(cat.get());
        produto.setDataCadastro(dto.getDataCadastro());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setValorUnitario(dto.getValorUnitario());
        produto.setImagem(file.getBytes());
        

        produto = produtoRepository.save(produto);

        return inserirUriDaImagem(produto);
    }

    public Boolean delete(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
