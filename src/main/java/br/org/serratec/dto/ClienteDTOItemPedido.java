package br.org.serratec.dto;

import br.org.serratec.model.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTOItemPedido {
    
    private String email;
    private String nomeCompleto;
    

    public ClienteDTOItemPedido(Cliente cliente) {
        this.email = cliente.getEmail();
        this.nomeCompleto = cliente.getNomeCompleto();
    }
}
