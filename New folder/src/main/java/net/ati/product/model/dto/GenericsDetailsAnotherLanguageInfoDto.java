/**
 * @Since {date}
 * @Name {file_name}
 * @Author Nayeemul Islam
 * @Project {project_name}
 */
package net.ati.product.model.dto;

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
public class GenericsDetailsAnotherLanguageInfoDto {
	
	private String rowLanguageCode;
	
	private int genericAttributeHeadId;
	
	private String genericHeadDetails;
	
	private GenericsDto generic;

}
