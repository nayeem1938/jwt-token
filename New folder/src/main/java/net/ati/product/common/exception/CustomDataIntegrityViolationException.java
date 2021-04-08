/**
 * 
 */
package net.ati.product.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nayeemul
 *
 */

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomDataIntegrityViolationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomDataIntegrityViolationException(String message) {
		super(message);
	}

}
