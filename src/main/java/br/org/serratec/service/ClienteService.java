package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.config.MailConfig;
import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.dto.ClienteListDTO;
import br.org.serratec.dto.EnderecoInserirDTO;
import br.org.serratec.exception.CpfException;
import br.org.serratec.exception.EmailException;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.ClienteRepository;
// import br.org.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    // @Autowired
    // private EnderecoRepository enderecoRepository;

    @Autowired
    private MailConfig mailConfig;

    public List<ClienteListDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteListDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clientesDTO.add(new ClienteListDTO(cliente));
        }

        return clientesDTO;
    }

    public ClienteListDTO buscar(Long id) {
        Optional<Cliente> clientes = clienteRepository.findById(id);
        if (!clientes.isPresent()) {
            return null;
        }
        return new ClienteListDTO(clientes.get());
    }

    public ClienteDTO inserir(ClienteInserirDTO clt) {

        if (clienteRepository.findByEmail(clt.getEmail()) != null) {
            throw new EmailException("Email já cadastrado");
        }
        if (clienteRepository.findByCpf(clt.getCpf()) != null) {
            throw new CpfException("Cpf já cadastrado");
        }

        EnderecoInserirDTO endereco = clt.getEndereco();
        Endereco enderecoViaCep = enderecoService.salvar(endereco.getCep(), endereco.getComplemento(),
                endereco.getNumero());

        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(clt.getNomeCompleto());
        cliente.setEmail(clt.getEmail());
        cliente.setCpf(clt.getCpf());
        cliente.setTelefone(clt.getTelefone());
        cliente.setDataNascimento(clt.getDataNascimento());
        cliente.setEndereco(enderecoViaCep);
        cliente = clienteRepository.save(cliente);

        mailConfig.sendEmail(clt.getEmail(), "Cadastro de Usuário",
                cliente.toString());

        return new ClienteDTO(cliente);
    }

    public ClienteDTO atualizar(Long id, ClienteInserirDTO clienteInserirDTO) {

        EnderecoInserirDTO endereco = clienteInserirDTO.getEndereco();
        Endereco enderecoViaCep = enderecoService.atualizar(endereco.getCep(), endereco.getComplemento(),
                endereco.getNumero(), id);

        clienteInserirDTO.setId(id);

        Cliente novoCliente = new Cliente();
        novoCliente.setId(clienteInserirDTO.getId());
        novoCliente.setEmail(clienteInserirDTO.getEmail());
        novoCliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
        novoCliente.setCpf(clienteInserirDTO.getCpf());
        novoCliente.setTelefone(clienteInserirDTO.getTelefone());
        novoCliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
        novoCliente.setEndereco(enderecoViaCep);

        novoCliente = clienteRepository.save(novoCliente);

        return new ClienteDTO(novoCliente);
    }

    public Boolean delete(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        // Optional<Endereco> endereco = enderecoRepository.findById(id);
        // && endereco.isPresent()
        if (cliente.isPresent() ) {
            clienteRepository.deleteById(id);
            // enderecoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}