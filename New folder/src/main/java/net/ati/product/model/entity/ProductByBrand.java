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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name = "pm_pbybrand", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "pbrands_no",name = "uk_pbrands_no")},
	indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_pbrandS_id", columnList = "pbrands_id"),
		  @Index(name = "uk_pbrandS_no", columnList = "pbrands_no"),
	//	  @Index(name = "pk_rlang_code_ov", columnList = "rlang_code_ov")
		  })
public class ProductByBrand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name= "pbrands_id"))
//	@AttributeOverride(name = "rlang_code_ov", column = @Column(name= "rlang_code"))
	private Identity productByBrandId;
	
	@NotNull(message = "Product By Brand No can't be null")
	@Column(name = "pbrands_no", unique = true)
	private String productByBrandNo;
	
	@Column(name = "brand_name")
	@Size(max = 150, message = "Brand name can't be more then {max}")
	private String brandName;
	
//	@NotNull(message = "Generics can't be null")
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//	@JoinColumn(name = "generic_no", referencedColumnName = "generic_no", nullable = false)
//	private Generics generic;
	

	@Digits(fraction = 0,integer = 1)
	@Column(name = "recshow_fg")
	private int recordShowFlag; 
	
	@Digits(fraction = 0,integer = 1)
	@Column(name = "astatus_fg")
	@NotNull(message = CustomMessage.NOT_NULL)
	private int activeStatusFlag;
	
	@Column(name = "userdsl_no")
	private Long userDefineSerialNumber;
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "create_at", updatable = false)
	private Date createdAt;
	
	@Column(name = "updated_by", insertable = false)
	private Long updatedBy;
	
	@Column(name = "updated_at", insertable = false)
	private Date updatedAt;
	
	@Size(max = 16, message = "can't be more than {max}")
	@Column(name = "user_pc_ip")
	@NotNull(message = "can't be Null")
	private String userPcIp;
	
	@Column(name = "session_id")
	@NotNull(message = "can't be Null")
	@Size(max = 30, message = "Size can't be more than {30}")
	private String sessionId;
	
	public void setProductByBrandNo(String rowLanCode, Long id)
	{
		this.productByBrandNo = rowLanCode+id;
	}
}
