package br.org.serratec.model;

import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.org.serratec.enums.Status;
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
	private Status status;

	@Column(name = "valor_total")
	private Double valorTotal = 0.0;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private Set<ItemPedido> itens = new HashSet<>();

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
