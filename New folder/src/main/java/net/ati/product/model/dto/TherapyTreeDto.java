/**
 * @Since Feb 17, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeem
 *
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TherapyTreeDto {
	
	
	private Long TherapyTreeId;
	
	private String therapyNo;
	
	private String therapyName;

	private Long parentid;
	
	private String parentTherapyName;
	
	private int recordShowFlag ;

	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Date updatedAt;

	private String userPcIp;

	private String sessionId;

}
