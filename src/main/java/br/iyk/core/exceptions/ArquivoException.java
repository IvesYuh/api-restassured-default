package br.iyk.core.exceptions;

public class ArquivoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ArquivoException(String mensagem) {
		super(mensagem);
	}

}
