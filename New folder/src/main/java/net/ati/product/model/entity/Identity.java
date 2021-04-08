/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeemul
 *
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identity implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;
	
	@Size(max = 2, message = "Size must be in {max} Charecter")
	@Column(name = "rlang_code")
	private String rowLanguageCode;

	
	public Identity(String rowLanguageCode) {
		this.rowLanguageCode = rowLanguageCode;
	}

}
