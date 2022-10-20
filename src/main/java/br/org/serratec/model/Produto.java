package br.org.serratec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.Data;

@Entity
@Table(name="produto")
@Data
public class Produto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
	private Long idProduto;
	
	@NotBlank(message = "Nome inválido")
	@Column(name="nome", nullable=false , length=30)
	private String nome;
	
	@Column(name="descricao", nullable=false , length=200)
	private String descricao;
	
	@NotNull
	@Column(name="qtd_estoque", nullable=false)
	private Integer qtdEstoque;
	
	@Past
	@Column(name="data_cadastro")
	private Date dataCadastro;
    
	@NotNull
	@Column(name="valor_unitario", nullable=false , length=10)
	private Double valorUnitario;

    @Lob
    @Column(name="imagem", nullable = false)
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
}