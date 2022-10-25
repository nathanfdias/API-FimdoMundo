package br.org.serratec.dto;

import javax.validation.constraints.NotNull;

import br.org.serratec.model.ItemPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemPedidoInserirDTO {

    private Long id;

    @NotNull(message = "Digite uma quantidade valida")
    private Integer quantidade;

    private Double percentualDesconto;
    // private Double valorBruto;
    // private Double valorLiquido;

    private ProdutoInserirPedidoDTO pedido;
    
    private ProdutoInserirItemDTO produto;

    public ItemPedidoInserirDTO(ItemPedido itemPedido) {
        this.id = itemPedido.getId();
        this.quantidade = itemPedido.getQuantidade();
        this.percentualDesconto = itemPedido.getPercentualDesconto();
        // this.valorBruto = itemPedido.getValorBruto();
        // this.valorLiquido = itemPedido.getValorLiquido();
        // this.quantidade = itemPedido.getQuantidade();
        this.pedido = new ProdutoInserirPedidoDTO(itemPedido.getPedido());
        this.produto = new ProdutoInserirItemDTO(itemPedido.getProduto());
    }
}
