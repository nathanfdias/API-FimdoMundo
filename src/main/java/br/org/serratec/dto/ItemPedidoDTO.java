package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;
import lombok.Data;

@Data
public class ItemPedidoDTO {

	private Integer quantidadeItem;
	private Double precoVenda;
    private Double valorBruto;
    private Double percentualDesconto;
	private Double valorLiquido;
	private ProdutoDTO produto;
	private PedidoDTO pedido;

	public ItemPedidoDTO(ItemPedido itemPedido){
		this.quantidadeItem = itemPedido.getQuantidadeItem();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.valorBruto = itemPedido.getValorBruto();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorLiquido = itemPedido.getValorLiquido();
		this.pedido = new PedidoDTO(itemPedido.getPedido());
        this.produto = new ProdutoDTO(itemPedido.getProduto());
	}
}
