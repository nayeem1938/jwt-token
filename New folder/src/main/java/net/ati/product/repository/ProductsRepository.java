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

import net.ati.product.model.dto.ProductMobileDetailsDto;
import net.ati.product.model.entity.Products;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
	
//	@Query(value = "select max(product_id) from pm_products", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PRODUCTS_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();
	
	Boolean existsByProductNo(String productNo);
	Boolean existsByProductIdId(Long id);
	
	Products findByProductNo(String productNo);
	
	List<Products> findByProductIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	List<Products> findByShowProductNewFlagAndRecordShowFlag(int flag, int showFlag);
	List<Products> findByProductIdIdAndRecordShowFlag(Long id ,int showFlag);
	List<Products> findByRecordShowFlag(int showFlag);
	
	@Query(value = "select * from  pmv_product", nativeQuery = true)
	List<ProductMobileDetailsDto> allDataForMobile();
}
