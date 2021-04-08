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
import net.ati.product.model.entity.ProductUnitOfMeasurmentInfo;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface ProductUnitOfMeasurmentInfoRepository extends JpaRepository<ProductUnitOfMeasurmentInfo, Long> {

	
//	@Query(value = "select max(produom_id) from pm_puominfo", nativeQuery = true)
	
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_PUOMINFO_SEQ') from dual", nativeQuery = true)

//	@Query(value = "SELECT function(FNC_PRIMEKEY('PM_PUOMINFO_SEQ'))", nativeQuery = false)
	Long lastIdValue();
	
	ProductUnitOfMeasurmentInfo findByProductUomNo(String ProductUomNoNo);

	List<ProductUnitOfMeasurmentInfo> findByProductUomIdIdAndRecordShowFlag(Long id, int showFlag);

	List<ProductUnitOfMeasurmentInfo> findByProductUomIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	
	List<ProductUnitOfMeasurmentInfo> findByRecordShowFlag(int showFlag);
	

	boolean existsByProductUomIdId(Long id);
	
	boolean existsByProductUomNo(String ProductUomNoNo);

}
