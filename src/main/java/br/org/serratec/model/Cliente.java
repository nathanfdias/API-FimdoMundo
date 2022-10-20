package br.org.serratec.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="cliente")
public class Cliente {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@NotBlank(message="Preencha o nome")
	@Size(max=60)
	@Column(name = "nome_completo", nullable=false, length=50)
	private String nomeCompleto;
	
	@NotBlank(message="E-mail é obrigatório.")
	@Size(max=50)
	@Email(message="Email inválido")
	@Column(name = "email", nullable=false, length=80)
	private String email;
	
	@NotBlank(message="CPF é obrigatório")
	@CPF(message="CPF inválido")
	@Column(name = "cpf", nullable=false, length=11)
	private String cpf;

    @NotBlank(message="Telefone Obrigatório")
    @Column(name="telefone", nullable = false, length=40)
    private String telefone;
	
    @Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

    @OneToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;


    public Cliente() {
    }
    
    public Long getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(idCliente, other.idCliente);
    }
}
