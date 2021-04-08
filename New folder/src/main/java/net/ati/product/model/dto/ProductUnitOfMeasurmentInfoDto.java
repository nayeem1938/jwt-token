/**
 * 
 */
package net.ati.product.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayeemul
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUnitOfMeasurmentInfoDto {
	
	private IdentityDto productUomId;
	
//	private String unitOfMeasurmentNo;

	private String productUomNo;
	
	private String unitOfMeasurmentName;
	
	private String userDefineUnitOfMeasurmentCode;
	
	private String unitOfMeasurmentComment;

	private String productStrengthFlag;
	
	private int recordShowFlag ;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private String createdAt;
	
	private Long updatedBy;
	
	private String updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<ProductUomAnotherLanguageinfo> anotherLanguageinfos;

}
