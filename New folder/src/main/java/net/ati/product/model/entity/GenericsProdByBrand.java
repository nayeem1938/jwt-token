/**
 * @Since Feb 17, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.entity
 */
package net.ati.product.model.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ati.product.common.message.CustomMessage;

/**
 * @author Nayeem
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PM_GWBRANDS")
public class GenericsProdByBrand {
	
	
	@Id
	@Column(name = "GWBRAND_ID")
	private Long genericsProdByBrandId;
	
	@NotNull(message = "Generics can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_generic_no005"), name = "generic_no", referencedColumnName = "generic_no", nullable = false)
	private Generics generic;
		
	@NotNull(message = "Therapy can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "pbrands_no",referencedColumnName = "pbrands_no", nullable = false)
	private ProductByBrand productByBrand;
	

	@Digits(fraction = 0, integer = 1)
	@Column(name = "recshow_fg")
	private int recordShowFlag ;
	
	@Digits(fraction = 0, integer = 1)
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

}
