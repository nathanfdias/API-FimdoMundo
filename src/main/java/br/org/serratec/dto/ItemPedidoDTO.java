package br.org.serratec.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemPedidoDTO {

    private Integer idProduto;
	private String nomeProduto;

	@NotNull
	@Min(1)
	private Integer quantidadeItem;

	@NotNull
	@Min(0)
	private Double precoVenda;

    @NotNull
	@Min(0)
    private Double valorBruto;

	@Min(0)
    private Double percentualDesconto;

    @NotNull
	@Min(0)
	private Double valorLiquido;
}
