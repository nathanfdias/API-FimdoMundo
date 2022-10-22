package br.org.serratec.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.org.serratec.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoInserirDTO {

    private Long id;

    @NotBlank(message = "Digite um nome valido para o produto")
    @Size(max = 30)
    private String nome;

    @NotBlank(message = "Digite um descrição valido para o produto")
    @Size(max = 200)
    private String descricao;

    @NotNull(message = "Digite uma quantidade valida")
    private Integer quantidadeEstoque;

    private LocalDate dataCadastro;

    @NotNull(message = "Digite um valor valido para o produto")
    private Double valorUnitario;
    
    private ProdutoInserirCategoria categoria;

    public ProdutoInserirDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = new ProdutoInserirCategoria(produto.getCategoria());
    }
}
