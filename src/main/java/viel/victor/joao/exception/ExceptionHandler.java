package viel.victor.joao.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Método responsável por tratar a excessão do tipo HttpMessageNotReadableException,
	 * caso a requisição feita contenha atributos ou valores que não exista no modelo criado.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagemUsuario = "Atributo(s) Inválido(s)";
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Método responsável por tratar a excessão do tipo MethodArgumentNotValidException,
	 * caso o tipo de argumento passado na requisição seja inválido, por exemplo 'null'.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	/**
	 * Método responsável por tratar a excessão do tipo EmptyResultDataAccessException,
	 * caso o objeto que deseje manipular não exista.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		
		String mensagemUsuario = "Recurso não encontrado";
		
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	/**
	 * Método responsável por tratar a excessão do tipo DataIntegrityViolationException,
	 * caso a requisão contenha o id de um objeto de relacionamento que não exista.
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String mensagemUsuario = "Id para relacionamento não existe";
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Método responsável por criar uma lista de Erros, sendo tratada nos métodos especifícos
	 * de cada excessão.
	 */
	private List<Erro> criarListaDeErros(BindingResult bindingResult) {
		List<Erro> erros = new ArrayList<>();
		
		for( FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new Erro(mensagemUsuario));
		}
		return erros;
	}
	
	/**
	 * Classe responsável por gerar um objeto para que a lista de erros ou mensagens 
	 * sejá exibida no momento da requisição.
	 *
	 */
	public static class Erro {
		
		private String mensagemUsuario;
		
		public Erro (String mensagemUsuario) {
			super();
			this.mensagemUsuario = mensagemUsuario;

		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}


	}
}
