/**
 * @Since Feb 17, 2021
 * @Author Nayeemul Islam
 * @Project drug-product-microservice-resourse-server
 * @Package net.ati.product.repository
 */
package net.ati.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.ati.product.model.entity.GenericsProdByBrand;

/**
 * @author Nayeem
 *
 */
public interface GenericsProdByBrandRepository extends JpaRepository<GenericsProdByBrand, Long> {

	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('PM_GWBRANDS_SEQ') from dual", nativeQuery = true)
	Long lastIdValue();

	boolean existsByGenericsProdByBrandId(Long id);
	
	GenericsProdByBrand findByGenericsProdByBrandIdAndRecordShowFlag(Long id ,int showFlag);

	List<GenericsProdByBrand> findByRecordShowFlag(int showFlag);
	
}
