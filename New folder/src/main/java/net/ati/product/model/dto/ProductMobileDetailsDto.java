/**
 * @Since Jan 20, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.dto
 */
package net.ati.product.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Nayeem
 *
 */

@Data
@Entity(name = "pmv_product")
public class ProductMobileDetailsDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "product_id")
//	@Digits(fraction = 0, integer = 14)
	private BigDecimal productId;
	
	@Column(name = "product_nm")
	private String productName;
	
	@Column(name = "PRODUCT_NO")
	private String productNo;
	
	@Column(name = "prod_descr")
	private String productDescription;
	
	@Column(name = "ptgprod_no")
	private String parentProductNo;
	
	@Column(name = "produom_no")
	private String productUnitOfMeasurmentNo;
	
	@Column(name = "produom_nm")
	private String productUnitOfMeasurmentName;
	
	@Column(name = "generic_no")
	private String genericNo;
	
	@Column(name = "generic_nm")
	private String genericName;
	
	@Column(name = "pstrengths")
	private String productStrength;
	
	@Column(name = "strunit_id")
	private int strengthUnitId;

	@Column(name = "prod_tstrs")
	private int productTotalStrength;
	
	@Column(name = "prodtyp_no")
	private String productTypeNo;
	
	@Column(name = "prodtyp_nm")
	private String productTypeName;
	
	@Column(name = "prodcat_no")
	private String productCatagoryNo;
	
	@Column(name = "prodcat_nm")
	private String productCatagoryName;
	
	@Column(name = "swnpnew_fg")
	private int showProductNewFlag;

	@Column(name = "product_fg")
	private Integer productFlag;
	
	@Column(name = "ud_prod_no")
	private String userDefineProductNo;
	
	@Column(name = "recshow_fg")
	private int recordShowFlag ;
	
	@Column(name = "astatus_fg")
	private int activeStatusFlag;	
	
	@Column(name = "prodimg_ln")
	private String thumbLink;
	
	@Column(name= "rlang_code")
	private String rowLanguageCode;
	
	@Column(name = "PROD_CATYP")
	private String categoryType;
	
}
