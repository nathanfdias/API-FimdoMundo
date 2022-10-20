package br.org.serratec.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.org.serratec.relacaoPedido.ItemPedidoPk;
import lombok.Data;

@Entity
@Table(name="item_pedido")
@Data
public class ItemPedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Min(1)
    @Column(name="quantidade", nullable = false)
    private Integer quantidadeItem;

    @NotNull
	@Column(name="preco_venda")
	private Double precoVenda;

    @NotNull
	@Column(name="percentual_desconto")
	@Min(0)
	private Double percentualDesconto;

    @NotNull
	@Column(name="valor_bruto")
	private Double valorBruto;

    @NotNull
	@Column(name="valor_liquido")
	private Double valorLiquido;
    
    @EmbeddedId
    private ItemPedidoPk itemPedidoPk = new ItemPedidoPk();
    // @Transient
	// public Double getSubTotal() {
	// 	return valor * qtdItens;
	// }
}
