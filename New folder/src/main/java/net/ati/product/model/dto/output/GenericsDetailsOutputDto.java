/**
 * @Since Mar 31, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto.output
 */
package net.ati.product.model.dto.output;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ati.product.model.dto.GenericsDetailsAnotherLanguageInfoDto;
import net.ati.product.model.dto.GenericsDto;
import net.ati.product.model.dto.IdentityDto;

/**
 * @author Nayeem
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericsDetailsOutputDto {
	
private IdentityDto genericsDetailsId;
	
	private String genericsDetailsNo;
	
	private int genericAttributeHeadId;
	
	private String genericAttributeHeadname;

	private GenericsDto generic;

	private String genericHeadDetails;

	private int recordShowFlag;

	private int activeStatusFlag;

	private Long userDefineSerialNumber;

	private Long createdBy;

	private Date createdAt;

	private Long updatedBy;

	private Date updatedAt;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<GenericsDetailsAnotherLanguageInfoDto> AnotherLanguageInfo;

}
