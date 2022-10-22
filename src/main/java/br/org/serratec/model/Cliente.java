package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	
	@NotBlank(message="Preencha o nome")
	@Size(max=60)
	@Column(name = "nome_completo", nullable=false, length=50)
	private String nomeCompleto;
	
	@NotBlank(message="E-mail é obrigatório.")
	@Size(max=50)
	@Email(message="Email inválido")
	@Column(name = "email", nullable=false, length=80)
	private String email;
	
	@CPF(message="CPF inválido")
	@Column(name = "cpf", nullable=false, length=11)
	private String cpf;

    @NotBlank(message="Telefone Obrigatório")
    @Column(name="telefone", nullable = false, length=40)
    private String telefone;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @Override
	public String toString() {
		return "Nome: " + nomeCompleto + "\nEmail: " + email + "\nCpf: " + cpf
				+ "\nTelefone: " + telefone + "\nData nascimento: " + dataNascimento + "\nEndereco: " + endereco;
	}
}
