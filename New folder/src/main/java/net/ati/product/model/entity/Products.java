/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ati.product.common.message.CustomMessage;


/**
 * @author Nayeemul
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pm_products",
	uniqueConstraints = {@UniqueConstraint(columnNames = "product_no",name = "uk_product_no")},
	indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_product_id", columnList = "product_id"),
		  @Index(name = "uk_product_no", columnList = "product_no"),
		  @Index(name = "fk_generic_no003", columnList = "generic_no"),
		  @Index(name = "fk_produom_no001", columnList = "produom_no"),
		  @Index(name = "fk_prodtyp_no001", columnList = "prodtyp_no")})
public class Products implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name = "product_id"))
	private Identity productId;
	
	@NotNull(message = "Product No No can't be null")
	@Column(name = "product_no", unique = true)
	private String productNo;
	
	@Column(name = "product_nm")
	@Size(max = 150, message = "product name can't be more then {max}")
	private String productName;
	
	
	@Size(max = 2000, message = "product name can't be more then {max}")
	@Column(name = "prod_descr")
	private String productDescription;
	

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ptgprod_no", referencedColumnName = "product_no", nullable = true)
	private Products parentProductNo;
	
	@NotNull(message = "Peroduct Unit Of Measurement No can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "produom_no", referencedColumnName = "produom_no")
	private ProductUnitOfMeasurmentInfo productUnitOfMeasurment;
	
	@Column(name = "pstrengths")
	private String productStrength;
	
	@NotNull(message = "Generics can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "generic_no", referencedColumnName = "generic_no", nullable = false)
	private Generics generic;
	
	@NotNull(message = "Product Type can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "prodtyp_no", referencedColumnName = "prodtyp_no", nullable = false)
	private ProductType productType;
	
	@NotNull(message = "Product By Brand can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "pbrands_no", referencedColumnName = "pbrands_no", nullable = false)
	private ProductByBrand productByBrand; 
	
	@Column(name = "strunit_id")
	private int strengthUnitId;
	
	@NotNull(message = "Peroduct Unit Of Measurement Id can't be null")
	@Column(name = "prod_tstrs")
	private int productTotalStrength;
	
	@Digits(fraction = 0, integer = 1)
	@Column(name = "product_fg")
	private Integer productFlag;
	
	@Column(name = "ud_prod_no")
	@Size(max = 16, message = "User Define Product No can't be more than {max}")
	private String userDefineProductNo;
	
	@Digits(fraction = 0, integer = 1)
	@Column(name = "recshow_fg")
	private int recordShowFlag ;
	
	@NotNull(message = CustomMessage.NOT_NULL)
	@Column(name = "astatus_fg")
	@Digits(fraction = 0, integer = 1)
	private int activeStatusFlag;
	
	@Column(name = "userdsl_no")
	private int userDefineSerialNumber;
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "created_at", updatable = false)
	private Date createdAt;
	
	@Column(name = "updated_by", insertable = false)
	private Long updatedBy;
	
	@Column(name = "updated_at", insertable = false)
	private Date updatedAt;
	
	@Digits(fraction = 0, integer = 1)
	@Column(name = "swnpnew_fg")
	private int showProductNewFlag;
	
	@Column(name = "PRODIMG_LN", nullable = true)
	@Size(max = 500, message = "Product Image Length can't be {max}")
	private String thumbLink;

	@Size(max = 16, message = "can't be more than {max}")
	@Column(name = "user_pc_ip")
	@NotNull(message = "can't be Null")
	private String userPcIp;
	
	@Column(name = "session_id")
	@NotNull(message = "can't be Null")
	@Size(max = 30, message = "Size can't be more than {30}")
	private String sessionId;
	

	
	@NotNull(message = "Product Category can't be null")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "prodcat_no", referencedColumnName = "prodcat_no", nullable = false)
	private ProductCategory productCatagory;
	
	@Column(name = "dgdadar_no")
	private String drugAdminRecordNo;
	
	@Column(name = "dgdadar_dt")
	private Date drugAdminRecordDate;
	
	@Digits(fraction = 2, integer = 5)
	@Column(name = "utrd_price")
	private Double unitTradingPrice;
	
	@Digits(fraction = 2, integer = 5)
	@Column(name = "utrd_pwvat")
	private Double unitTradingPriceWithVat;
	
	@Digits(fraction = 2, integer = 5)
	@Column(name = "unit_price")
	private Double unitPrice;
	
	@Column(name = "PROD_CATYP")
	private String categoryType;
	
	
	
	public void setProductNo(String rowLanCode, Long id)
	{
		this.productNo = rowLanCode+id;
	}

}
