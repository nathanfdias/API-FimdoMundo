package br.org.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.config.MailConfig;
import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.dto.RelatorioDTO;
import br.org.serratec.service.PedidoService;

@RestController
@RequestMapping("relatorio")
public class RelatorioController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private MailConfig mailConfig;

    @GetMapping("{id}")
    public ResponseEntity<RelatorioDTO> enviarRelatorio(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.buscar(id);

        RelatorioDTO relatorio = new RelatorioDTO(pedido);

        if (pedido != null) {
            mailConfig.sendEmail(pedido.getCliente().getEmail(), "Dados do Pedido", relatorio.toString());
            // mailConfig.sendEmail("nathan.dias@aluno.senai.br", "Dados do Pedido",
            // relatorio.toString());
            return ResponseEntity.ok(relatorio);

        }

        return ResponseEntity.notFound().build();
    }
}
