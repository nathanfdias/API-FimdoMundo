package br.org.serratec.dto;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemPedidoDTOItens {
    
    private Long id;
    private Integer quantidade;
    private Double percentualDesconto;
    private Double valorBruto;
    private Double valorLiquido;


    public ItemPedidoDTOItens(ItemPedido itemPedido){
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.percentualDesconto = itemPedido.getPercentualDesconto();
        this.valorBruto = itemPedido.getValorBruto();
        this.valorLiquido = itemPedido.getValorLiquido();
    }
}
