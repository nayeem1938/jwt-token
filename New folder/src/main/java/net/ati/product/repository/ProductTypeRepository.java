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
import net.ati.product.model.entity.ProductType;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	
//	@Query(value = "select max(prodtyp_id) from pm_ptypinfo", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PTYPINFO_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();
	
	ProductType findByProductTypeNo(String productTypeNo);
	
	List<ProductType> findByProductTypeIdId(Long id);
	
	boolean existsByProductTypeIdId(Long id);
	
	boolean existsByProductTypeNo(String productTypeNo);
	
	List<ProductType> findByProductTypeIdRowLanguageCode(String language);

}
