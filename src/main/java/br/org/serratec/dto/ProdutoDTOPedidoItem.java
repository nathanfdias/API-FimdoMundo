package br.org.serratec.dto;

import br.org.serratec.model.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTOPedidoItem {
    
	private String nome;
	private Integer quantidadeEstoque;
	private Double valorUnitario;

    private CategoriaDTOonName categoria;


    public ProdutoDTOPedidoItem(Produto produto){
        this.nome = produto.getNome();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.valorUnitario = produto.getValorUnitario();
        this.categoria = new CategoriaDTOonName(produto.getCategoria());
    }

}
