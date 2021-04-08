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

import net.ati.product.model.entity.ProductByBrand;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface ProductByBrandRepository extends JpaRepository<ProductByBrand, Long> {
	
//	@Query(value = "select max(pbybrand_id) from pm_pbybrand", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PBYBRAND_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();
	
	Boolean existsByProductByBrandNo(String productByBrandNo);
	Boolean existsByProductByBrandIdId(Long id);
	
	ProductByBrand findByProductByBrandNo(String productByBrandNo);
	
	List<ProductByBrand> findByProductByBrandIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	List<ProductByBrand> findByProductByBrandIdIdAndRecordShowFlag(Long id ,int showFlag);
	List<ProductByBrand> findByRecordShowFlag(int showFlag);
}
