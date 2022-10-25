package br.org.serratec.exception;

public class QuantidadeEstoqueException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public QuantidadeEstoqueException(String message) {
        super(message);
    }
    
}
