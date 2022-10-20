package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Cliente;

public class ClienteDTO {

    private Long idCliente;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;

    public ClienteDTO() {
    }
  
    public ClienteDTO(String nomeCompleto, String email, String cpf,String telefone, LocalDate dataNascimento) {
      this.nomeCompleto = nomeCompleto;
      this.email = email;
      this.cpf = cpf;
      this.telefone = telefone;
      this.dataNascimento = dataNascimento;
    }
  
    public ClienteDTO(Cliente cliente) {
      nomeCompleto = cliente.getNomeCompleto();
      email = cliente.getEmail();
      cpf = cliente.getCpf();
      telefone = cliente.getTelefone();
      dataNascimento = cliente.getDataNascimento();
      endereco = new EnderecoDTO(cliente.getEndereco());
  
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

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
