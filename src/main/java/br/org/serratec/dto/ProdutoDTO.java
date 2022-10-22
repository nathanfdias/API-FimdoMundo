package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO {
    
    private Long id;
	private String nome;
	private String descricao;
	private Integer quantidadeEstoque;
	private LocalDate dataCadastro;
	private Double valorUnitario;

    private Categoria categoria;
    // private Byte[] imagem;
    private String uri;

    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = produto.getCategoria();
    }

}
