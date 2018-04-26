package com.montanha.gerenciador.services.exceptions;

public class ViagemNaoExisteException extends RuntimeException {

	private static final long serialVersionUID = -1402677565107062800L;

	public ViagemNaoExisteException(String mensagem) {
		super(mensagem);
	}

	public ViagemNaoExisteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
