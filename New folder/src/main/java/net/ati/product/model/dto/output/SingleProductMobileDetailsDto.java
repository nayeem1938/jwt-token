/**
 * @Since Feb 25, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto.output
 */
package net.ati.product.model.dto.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

/**
 * @author Nayeem
 *
 */
@Data
public class SingleProductMobileDetailsDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private BigDecimal productId;
	
	private String productName;
	
	private String productNo;
	
	private String productDescription;
	
	private String parentProductNo;
	
	private String productUnitOfMeasurmentNo;
	
	private String productUnitOfMeasurmentName;
	
	private String genericNo;
	
	private String genericName;
	
	private String productStrength;
	
	private int strengthUnitId;

	private int productTotalStrength;
	
	private String productTypeNo;
	
	private String productTypeName;
	
	private String productCatagoryNo;
	
	private String productCatagoryName;
	
	private int showProductNewFlag;

	private Integer productFlag;
	
	private String userDefineProductNo;
	
	private int recordShowFlag ;
	
	private int activeStatusFlag;	
	
	private String thumbLink;
	
	private String rowLanguageCode;
	
	private String categoryType;
	
	List<String> images;
}
