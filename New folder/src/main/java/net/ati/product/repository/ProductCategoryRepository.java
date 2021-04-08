/**
 * @Since Jan 21, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.ati.product.model.entity.Identity;
import net.ati.product.model.entity.ProductCategory;

/**
 * @author Nayeem
 *
 */

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Identity> {
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PRODCATG_SEQ') from dual", nativeQuery = true)
	Long idValue();
	
	ProductCategory findByCategoryNo(String categoryNo);
	
	List<ProductCategory> findByCategoryIdIdAndRecordShowFlag(Long id, int showFlag);
	
	boolean existsByCategoryIdId(Long id);
	
	boolean existsByCategoryNo(String genericsDetailsNo);
	
	List<ProductCategory> findByCategoryIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	
	List<ProductCategory> findByRecordShowFlag(int showFlag);

}
