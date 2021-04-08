/**
 * @Since Mar 29, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto.output
 */
package net.ati.product.model.dto.output;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import net.ati.product.model.dto.ProductMobileDetailsDto;

/**
 * @author Nayeem
 *
 */

@Data
@Entity(name = "PMV_PRODCAT")
public class GenericsDetailsHeadDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ATTRHAD_ID")
	private Integer headId;
	
	@Column(name = "ATTRHAD_NM")
	private String headName;
	
	@Column(name = "USERDSL_NO")
	private Integer userDefineSerialNumber;
	
	@Column(name = "ASTATUS_FG")
	private Integer activeStatusFlag;

}
