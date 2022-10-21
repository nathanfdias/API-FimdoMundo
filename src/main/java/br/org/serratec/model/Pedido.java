package br.org.serratec.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.org.serratec.enums.PedidoStatus;
import lombok.Data;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
	private Long idPedido;

	@Column(name = "data_pedido")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido;

	@Column(name = "data_entrega")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEntrega;
	
	@Column(name = "data_envio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvio;
	
    @Enumerated(EnumType.ORDINAL)
	@Column(name="status", nullable = false)
	private PedidoStatus pedidoStatus;

    @NotNull
	@Column(name = "valor_total")
	private Double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

	// @OneToMany(mappedBy = "id.pedido")
	// private Set<PedidoItem> itens = new HashSet<>();


	@Transient
	public Double getTotal() {
		double soma = 0.0;
		for(ItemPedido item : itens) {
			soma += item.getSubTotal();
		}
		return soma;
	}

}
