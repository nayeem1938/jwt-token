/**
 * @Since Jan 21, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.model.entity
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import net.ati.product.common.message.CustomMessage;

/**
 * @author Nayeem
 *
 */

@Data
@Entity
@Table(name = "pm_prodimgs")
public class ProductImage implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name = "prodimg_id"))
	private Identity imageId;
	
	@Column(name = "prodimg_no", nullable = false)
	private String imageNo;
	
	@Size(max = 1000, message = CustomMessage.MAX_SIZE)
	@Column(name = "prodimg_ln")
	private String imageLink;
	
	@NotNull(message = "Product can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "product_no", referencedColumnName = "product_no")
	private Products product;
	
	@NotNull(message = "Product Brand can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "pbrands_no", referencedColumnName = "pbrands_no")
	private ProductByBrand productByBrand;
	
	@Size(max = 20, message = CustomMessage.MAX_SIZE)
	@Column(name = "userdslimg_no")
	private String userDefineImageNo;
	
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

	/**
	 * @param rowLanguageCode
	 * @param id
	 */
	public void setImageNo(String rowLanguageCode, Long id) {
		this.imageNo = rowLanguageCode+id;
		
	}


}
