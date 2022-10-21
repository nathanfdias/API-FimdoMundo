package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="item_pedido")

public class ItemPedido {
    
    // @EmbeddedId
    // private ItemPedidoPk id = new ItemPedidoPk();

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_pedido")
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

	@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

	@ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    
    @Transient
	public Double getSubTotal() {
		return precoVenda * quantidadeItem;
	}
}
