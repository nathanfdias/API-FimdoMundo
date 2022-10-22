package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    private Endereco endereco;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeCompleto = cliente.getNomeCompleto(); 
        this.email = cliente.getEmail();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.dataNascimento = cliente.getDataNascimento();
        this.endereco = cliente.getEndereco();
    }

}
