/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.model.entity;

import java.io.Serializable;
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
 * @author Nayeemul
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pm_gtherapy"
// , uniqueConstraints = {@UniqueConstraint(columnNames = "gentgrp_no",name = "uk_gentgrp_no")},
//	indexes = {
//		  @Index(name = "cc_astatus_fg", columnList = "astatus_fg"),
//		  @Index(name = "pk_gentgrp_id", columnList = "gentgrp_id"),
//		  @Index(name = "uk_gentgrp_no", columnList = "gentgrp_no"),
//		  @Index(name = "fk_therapy_no001", columnList = "therapy_no"),
//		  @Index(name = "fk_generic_no001", columnList = "generic_no")}
)
public class GenericTherapy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	@AttributeOverride(name = "id", column = @Column(name = "gentgrp_id"))
//	private Identity genericTherapyId;
	
	@Id
	@Column(name = "gentgrp_id")
	private Long genericTherapyId;
	
//	@NotNull(message = "Generic Therapy No can't be null")
//	@Column(name = "gentgrp_no")
//	private String genericTherapyNo;
	
	
	@NotNull(message = "Generics can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_generic_no001"), name = "generic_no", referencedColumnName = "generic_no", nullable = false)
	private Generics generic;
		
	@NotNull(message = "Therapy can't be null")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_therapy_no001"), name = "therapy_no",referencedColumnName = "therapy_no", nullable = false)
	private Therapys therapy;
	

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
	
//	public void setGenericTherapyNo(String rowLanCode, Long id)
//	{
//		this.genericTherapyNo = rowLanCode+id;
//	}

}
