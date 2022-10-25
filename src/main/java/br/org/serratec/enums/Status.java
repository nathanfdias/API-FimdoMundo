// AGUARDANDO_PAGAMENTO(1),
// PAGO(2),
// ENVIADO(3),
// ENTREGUE(4),
// CANCELADO(5);
package br.org.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.exception.EnumValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public enum Status {

    AGUARDANDO_PAGAMENTO("1"),
	PAGO("2"),
	ENVIADO("3"),
	ENTREGUE("4"),
	CANCELADO("5");

    @Setter
    @Getter
    private String status;

    @JsonCreator
    public static Status verifica(String valor) throws EnumValidationException {
        for (Status estados : Status.values()) {
            if (valor.equals(estados.getStatus())) {
                return estados;
            }
        }
        throw new EnumValidationException("Preencha o Estados corretamente");
    }
}
