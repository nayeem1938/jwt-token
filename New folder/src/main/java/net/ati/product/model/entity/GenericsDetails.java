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
import javax.persistence.ForeignKey;
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
@Table(name = "pm_gdetails",
uniqueConstraints = {@UniqueConstraint(columnNames = "gdetail_no",name = "uk_gdetail_no")}
,indexes = {
		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
		  @Index(name = "pk_gdetail_id", columnList = "gdetail_id"),
		  @Index(name = "uk_gdetail_no", columnList = "gdetail_no")}
)
public class GenericsDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@EmbeddedId
	@AttributeOverride(name = "id", column = @Column(name = "gdetail_id"))
	private Identity genericsDetailsId;
	

	@NotNull(message = "Generics Details No can't be null")
	@Column(name = "gdetail_no")
	private String genericsDetailsNo;
	
	@Column(name = "attrhad_id")
	private int genericAttributeHeadId;  
	
	@NotNull(message = "Generics can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_generic_no"),name = "generic_no", referencedColumnName = "generic_no")
	private Generics generic;
	
	@Size(max = 4000, message = "Generic Head Details can't be more than {max]")
	@Column(name = "genhad_det")
	private String genericHeadDetails;
	
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
	
	
	public void setGenericsDetailsNo(String rowLanCode, Long id)
	{
		this.genericsDetailsNo = rowLanCode+id;
	}
	
	

}













