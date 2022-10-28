package br.org.serratec.dto;
import java.io.Serializable;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemPedidoRelatorioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    private Integer quantidade;
    private Double precoDesconto;
    private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
    private ProdutoItemPedidoListDTO produto;

    public ItemPedidoRelatorioDTO(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.percentualDesconto = itemPedido.getPercentualDesconto();
        this.valorBruto = itemPedido.getValorBruto();
        this.valorLiquido = itemPedido.getValorLiquido();
        this.produto = new ProdutoItemPedidoListDTO(itemPedido.getProduto());
    }

    @Override
    public String toString() {
        return "\nId Produto: " + getId() + 
        "\nQuantidade: " + getQuantidade() +
        "\nPercentual desconto: " + getPercentualDesconto() + "%" +
        "\nValor Bruto: " + getValorBruto() +
        "\nValor Liquido: " + getValorLiquido() +
        "\nProdutos: " + getProduto().toString();
    }
}
