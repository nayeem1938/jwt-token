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

/**
 * @author Nayeemul
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pm_ptypinfo",
	uniqueConstraints = {@UniqueConstraint(columnNames = "prodtyp_no",name = "uk_prodtyp_no")},
	indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_prodtyp_id", columnList = "prodtyp_id"),
		  @Index(name = "uk_prodtyp_no", columnList = "prodtyp_no")})
public class ProductType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name= "prodtyp_id"))
	private Identity productTypeId;
	
	@Column(name = "prodtyp_no", unique = true)
	private String productTypeNo;
	
	@Column(name = "prodtyp_nm")
	@Size(max = 100, message = "product Type Name can't more than {max}")
	private String productTypeName; 
	
	@Column(name = "ptype_desc")
	@Size(max = 2000, message = "product Type Description can't more than {max}")
	private String productTypeDescription;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ptgptyp_no", referencedColumnName = "prodtyp_no", nullable = true)
	private ProductType parentProductTypeNo;
	
	@Column(name = "ud_ptyp_no")
	@Size(max = 20, message = "User Define Product Type No can't be more than {max}")
	private String userDefineProductTypeNo;
	
	@Digits(fraction = 0,integer = 1)
	@Column(name = "recshow_fg")
	private int recordShowFlag; 
	
	@Digits(fraction = 0,integer = 1)
	@Column(name = "astatus_fg", nullable = false)
//	@NotNull(message = CustomMessage.NOT_NULL)
	private int activeStatusFlag;
	
	@Column(name = "userdsl_no")
	private Long userDefineSerialNumber;
	
	@Column(name = "created_by", updatable = false)
	private Long createdBy;
	
	@Column(name = "created_at", updatable = false)
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
	
	public void setProductTypeNo(String rowLanCode, Long id)
	{
		this.productTypeNo = rowLanCode+id;
	}
	

}






















