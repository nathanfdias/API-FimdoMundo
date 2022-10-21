package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.org.serratec.enums.PedidoStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
	private Long id;

	@Column(name = "data_pedido")
	private LocalDate dataPedido;

	@Column(name = "data_entrega")
	private LocalDate dataEntrega;
	
	@Column(name = "data_envio")
	private LocalDate dataEnvio;
	
    @Enumerated(EnumType.ORDINAL)
	@Column(name="status", nullable = false)
	private PedidoStatus pedidoStatus;

    @NotNull
	@Column(name = "valor_total")
	private Double valorTotal;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

    // @OneToMany(mappedBy = "id.pedido")
    // private Set<ItemPedido> itens = new HashSet<>();

	// @Transient
	// public Double getTotal() {
	// 	double soma = 0.0;
	// 	for(ItemPedido item : itens) {
	// 		soma += item.getSubTotal();
	// 	}
	// 	return soma;
	// }

}
