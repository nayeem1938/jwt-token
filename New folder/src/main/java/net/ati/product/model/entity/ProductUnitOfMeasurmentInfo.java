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
@Table(name = "pm_puominfo",
	uniqueConstraints = {@UniqueConstraint(columnNames = "produom_no",name = "uk_produom_no")},
	indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_produom_id", columnList = "produom_id"),
		  @Index(name = "uk_produom_no", columnList = "produom_no")
})
public class ProductUnitOfMeasurmentInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name = "produom_id"))
	private Identity productUomId;
	
	@Column(name = "produom_no", unique = true)
	private String productUomNo;
	
	@Column(name = "produom_nm")
	@Size(max = 100, message = "Product Unit Of Measurment Name can't be more then {max}")
	private String unitOfMeasurmentName;
	
	@Column(name = "uduom_code")
	@Size(max = 10, message = "User Define Unit Of Measurment Code can't be more then {max}")
	private String userDefineUnitOfMeasurmentCode;
	
	@Column(name = "uom_coment")
	@Size(max = 4000, message = "Unit Of Measurment Comment can't be more then {max}")
	private String unitOfMeasurmentComment;
	
	@Column(name = "prodstr_fg")
	@Size(max = 1, message = "Product Strength Flag must be in 1 char")
	private String productStrengthFlag;
	
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
	
	public void setProductUomNo(String rowLanCode, Long id)
	{
		this.productUomNo = rowLanCode+id;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
