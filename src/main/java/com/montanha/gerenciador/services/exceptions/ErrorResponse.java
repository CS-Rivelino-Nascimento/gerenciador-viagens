package com.montanha.gerenciador.services.exceptions;

import org.springframework.stereotype.Component;

/**
 * @author murilocarvalho
 */

@Component
public class ErrorResponse {

	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
