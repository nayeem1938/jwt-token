package net.ati.product.model.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {
	
	private IdentityDto productId;
	
	private String productNo;

	private String productName;
	
	private String productDescription;
	
	private String parentProductNo;
	
	private String parentProductName;
	
	private ProductUnitOfMeasurmentInfoDto productUnitOfMeasurment;
	
	private GenericsDto generic;

	private ProductTypeDto productType;
	
	private ProductByBrandDto productByBrand;
	
	private String productStrength;
	
	private int strengthUnitId;
	
	private int productTotalStrength;
	
	private Integer productFlag;
	
	private String userDefineProductNo;

	private int recordShowFlag ;
	
	private int activeStatusFlag;
	
	private Long userDefineSerialNumber;
	
	private Long createdBy;
	
	private Date createdAt;
	
	private Long updatedBy;
	
	private Date updatedAt;
	
	private int ShowProductNewFlag;
	
	private String productImageLength;
	
	private String userPcIp;
	
	private String sessionId;
	
	private List<ProductsAnotherLanguageDto> anotherLanguageinfo;

	private String productCatagoryNo;
	
	private String productCatagoryName;
	
	private String categoryType;
	
	private String drugAdminRecordNo;
	
	private Date drugAdminRecordDate;
	
	private Double unitTradingPrice;
	
	private Double unitTradingPriceWithVat;
	
	private Double unitPrice;
	
	private String thumbLink;
	
//	private List<String> productImageDownloadUrl;
	
	private List<String> productImageGetUrl;
	
	
}
