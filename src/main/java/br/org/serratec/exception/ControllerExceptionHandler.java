package br.org.serratec.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Object> handleEmailException(EmailException ex) {
        EmailException emailException = new EmailException(ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(emailException);
    }

    @ExceptionHandler(CpfException.class)
    public ResponseEntity<Object> handleCpfException(CpfException ex) {
        CpfException cpfException = new CpfException(ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(cpfException);
    }

    @ExceptionHandler(FindIdException.class)
    public ResponseEntity<Object> handleFindIdException(FindIdException ex) {
        FindIdException findIdException = new FindIdException(ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(findIdException);
    }

    @ExceptionHandler(SenhaException.class)
	public ResponseEntity<Object> senhaExceptionHandler(SenhaException ex){
		return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

    @ExceptionHandler(PedidoIdException.class)
    public ResponseEntity<Object> handlePedidoIdExceptionException(PedidoIdException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> erros = new ArrayList<>();
        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            erros.add(erro.getField() + ":" + erro.getDefaultMessage());
        }
        ErroResposta erroResposta = new ErroResposta(status.value(),
                "Preenchimento inv??lido", LocalDateTime.now(), erros);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErroResposta erroResposta = new ErroResposta(status.value(),
                "Existem campos inv??lidos. Confira o preenchimento", LocalDateTime.now(), null);

        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }
}
    