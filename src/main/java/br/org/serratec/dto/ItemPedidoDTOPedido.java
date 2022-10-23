package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDTOPedido {

	private Integer quantidadeItem;
	private Double precoVenda;
    private Double valorBruto;
    private Double percentualDesconto;
	private Double valorLiquido;


	public ItemPedidoDTOPedido(ItemPedido itemPedido){
		this.quantidadeItem = itemPedido.getQuantidadeItem();
		this.precoVenda = itemPedido.getPrecoVenda();
		this.valorBruto = itemPedido.getValorBruto();
		this.percentualDesconto = itemPedido.getPercentualDesconto();
		this.valorLiquido = itemPedido.getValorLiquido();

	}
}