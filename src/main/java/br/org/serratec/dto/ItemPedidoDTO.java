package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDTO {

	private Integer quantidadeItem;
	private Double precoVenda;
    private Double valorBruto;
    private Double percentualDesconto;
	private Double valorLiquido;
	private ProdutoDTOPedidoItem produto;
	private PedidoDTOITemPedido pedido;

	public ItemPedidoDTO(ItemPedido itemPedido){
		this.quantidadeItem = itemPedido.getQuantidadeItem();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.valorBruto = itemPedido.getValorBruto();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorLiquido = itemPedido.getValorLiquido();
		this.pedido = new PedidoDTOITemPedido(itemPedido.getPedido());
        this.produto = new ProdutoDTOPedidoItem(itemPedido.getProduto());
	}
}
