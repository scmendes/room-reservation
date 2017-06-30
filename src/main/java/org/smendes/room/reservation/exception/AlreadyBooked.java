package org.smendes.room.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excecao de negocio quando ja existir uma mesma sala reservada no periodo.
 * @author mendes
 *
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AlreadyBooked extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyBooked(String message) {
		super("AlreadyBooked exception: " + message + "'.");
	}

}
