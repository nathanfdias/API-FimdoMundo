package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="id_endereco")
    // private Long id;

    // @Column(name="cep", length=8)
    // private String cep;

    // @Column(name="rua", length=80)
    // private String rua;

    // @Column(name="bairro", length=50)
    // private String bairro;

    // @Column(name="cidade", length=80)
    // private String cidade;
    
    // @Column(name="numero", length=20)
    // private String numero;
    // // Talvez Integer para funcionar no viaCep

    // @Column(name="complemento", length=80)
    // private String complemento;

    // @Column(name="uf", length=80)
    // private String uf;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	private String cep;

	@Column(name = "rua")
	private String logradouro;

	private String complemento;

	private String bairro;

	@Column(name = "cidade")
	private String localidade;

	private String uf;

	private Integer numero;
}
