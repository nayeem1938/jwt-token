/**
 * Dec, 2020
 * AnotherDescriptionDto.java
 * @Author Nayeemul Islam
 * drug-product-microservice-resourse-server
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.ati.product.model.entity.GenericTherapy;

/**
 * @author Nayeemul
 *
 */


@Repository
public interface GenericTherapyRepository extends JpaRepository<GenericTherapy, Long> {
	
//	@Query(value = "select max(gentgrp_id) from pm_gtherapy", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_GTHERAPY_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();

	boolean existsByGenericTherapyId(Long id);
//	boolean existsByGenericTherapyNo(String genericTherapyNo);
	
//	GenericTherapy findByGenericTherapyNo(String genericTherapyNo);
	
//	List<GenericTherapy> findByGenericTherapyIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
//	List<GenericTherapy> findByGenericTherapyIdIdAndRecordShowFlag(Long id ,int showFlag);
	
	GenericTherapy findByGenericTherapyIdAndRecordShowFlag(Long id ,int showFlag);

	List<GenericTherapy> findByRecordShowFlag(int showFlag);
}
