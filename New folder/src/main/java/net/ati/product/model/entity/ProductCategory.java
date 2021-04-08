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
@Table(name = "pm_prodcatg")
public class ProductCategory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name= "prodcat_id"))
	private Identity categoryId;
	
	@NotNull(message = "Product Category No can't be null")
	@Column(name = "prodcat_no", unique = true)
	private String categoryNo;
	
	@Size(max = 120, message = "Product Category name can't be more then {max}")
	@Column(name = "prodcat_nm")
	private String categoryName;
	
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "PTGPCAT_NO", referencedColumnName = "prodcat_no",nullable = true)
	private ProductCategory parentCategory;
	
//	@Size(max = 20, message = "User Define Category No can't be more than {max}")
	@Column(name = "userdslcat_no")
	private Integer userDefineCategoryNo;
	
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
	
	@Column(name = "PROD_CATYP")
	@NotNull(message = "can't be Null")
	@Size(max = 3, message = "Size can't be more than {max}")
	private String categoryType;

	/**
	 * @param rowLanguageCode
	 * @param id
	 */
	public void setCategoryNo(String rowLanguageCode, Long id) {
		
		this.categoryNo = rowLanguageCode+id;
	}
	

}
