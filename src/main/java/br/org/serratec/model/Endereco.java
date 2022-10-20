package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_endereco")
    private Long idEndereco;

    @Column(name="cep", nullable=false, length=8)
    @NotBlank(message = "CEP inválido")
    private String cep;

    @Column(name="rua", nullable=false, length=80)
    @NotBlank(message = "Rua inválida")
    private String rua;

    @Column(name="bairro", nullable=false, length=50)
    @NotBlank(message = "Bairro inválido")
    private String bairro;

    @Column(name="cidade", nullable=false, length=80)
    @NotBlank(message = "Cidade inválido")
    private String cidade;
    
    @Column(name="numero", nullable=false, length=20)
    @NotBlank(message = "Numero inválido")
    private String numero;

    @Column(name="complemento", nullable=false, length=80)
    @NotBlank(message = "Complemento inválido")
    private String complemento;

    @Column(name="uf", nullable=false, length=80)
    @NotBlank(message = "UF inválido")
    private String uf;

// A comment.
    // @OneToOne
	// @JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
    // @JsonIgnore
	// private Cliente cliente;
    
    public Endereco(){
        
    }

    public Long getIdEndereco() {
        return idEndereco;
    }
    
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    // public Cliente getCliente() {
    //     return cliente;
    // }

    // public void setCliente(Cliente cliente) {
    //     this.cliente = cliente;
    // }
}
