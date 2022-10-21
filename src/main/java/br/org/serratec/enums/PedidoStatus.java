package br.org.serratec.enums;

import br.org.serratec.exception.EnumValidationException;

public enum PedidoStatus {
	
	AGUARDANDO_PAGAMENTO(1),
	PAGO(2),
	ENVIADO(3),
	ENTREGUE(4),
	CANCELADO(5);
	
	private int code;
	
	private PedidoStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static PedidoStatus valueOf(int code) throws EnumValidationException {
		for (PedidoStatus value : PedidoStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new EnumValidationException("Status Inválido");
	}
}
