/**
 * Dec 2020
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
@Table(name = "pm_generics", 
	uniqueConstraints = {@UniqueConstraint(columnNames = "generic_no",name = "uk_generic_no")}
,
	indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_generic_id", columnList = "generic_id"),
		  @Index(name = "uk_generic_no", columnList = "generic_no")}
)
public class Generics implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name ="generic_id"))
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generics_id_seq")
//	@SequenceGenerator(sequenceName = "generics_id_seq", allocationSize = 1, initialValue = 1001, name = "generics_id_seq")
	private Identity genericsId;
	
	@NotNull(message = "Generics No can't be null")
	@Column(name = "generic_no", unique = true)
	private String genericsNo;

	@Column(name = "generic_nm")
	@Size(max = 300, message = "Generics name can't be more then {max}")
	private String genericsName;
	
	
	@Column(name = "gnmbyingrs")
	@Size(max = 2000, message = "Generics name By Ingredient Name can't be more then {max}")
	private String genericNameByIngredientName;
	

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

	
	public void setGenericsNo(String rowLanCode, Long id)
	{
		this.genericsNo = rowLanCode+id;
	}
	

}






















