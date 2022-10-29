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
import br.org.serratec.dto.EnderecoDTO;
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

        clienteInserirDTO.setId(id);

        // Buscando o cliente que vai ser atualizado
        ClienteListDTO clientL = buscar(id);
        // Buscando o endereço que vai ser atualizado do cliente
        EnderecoDTO enderecoBuscarId = clientL.getEndereco();

        // verifica no banco de pode ser alterado o email
        if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) {
            if (!clienteRepository.findByEmail(clienteInserirDTO.getEmail()).getEmail().equals(clientL.getEmail())) {
                throw new EmailException("Email já existe na base");
            }
        }

        // não permite alteração do cpf do cliente
        // if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) != null) {
        //     if (!clienteRepository.findByCpf(clienteInserirDTO.getCpf()).getCpf().equals(clientL.getCpf())) {
        //         throw new CpfException("CPF não pode ser alterado");
        //     }
        // }

        if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) == null) {
            throw new CpfException("CPF não pode ser alterado");
        }

        // Atualizando o endereço do cliente passando o id do endereço que foi achando
        // acima e o dados novos
        EnderecoInserirDTO endereco = clienteInserirDTO.getEndereco();
        Endereco enderecoViaCep = enderecoService.atualizar(endereco.getCep(), endereco.getComplemento(),
                endereco.getNumero(), enderecoBuscarId.getId());

        Cliente novoCliente = new Cliente();
        novoCliente.setId(clienteInserirDTO.getId());
        novoCliente.setNomeCompleto(clienteInserirDTO.getNomeCompleto());
        novoCliente.setEmail(clienteInserirDTO.getEmail());
        novoCliente.setCpf(clienteInserirDTO.getCpf());
        novoCliente.setTelefone(clienteInserirDTO.getTelefone());
        novoCliente.setDataNascimento(clienteInserirDTO.getDataNascimento());
        novoCliente.setEndereco(enderecoViaCep);

        novoCliente = clienteRepository.save(novoCliente);

        // mailConfig.sendEmail(clienteInserirDTO.getEmail(), "Atualização de cadastro de Usuário",
        //         novoCliente.toString());

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