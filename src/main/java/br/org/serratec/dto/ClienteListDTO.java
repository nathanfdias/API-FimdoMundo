package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClienteListDTO {
    
    private Long id;
    private String email;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;

    public ClienteListDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco =  new EnderecoDTO(cliente.getEndereco());
    }
}
