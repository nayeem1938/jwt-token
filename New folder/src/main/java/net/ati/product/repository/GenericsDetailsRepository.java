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
import net.ati.product.model.entity.GenericsDetails;

/**
 * @author Nayeemul
 *
 */

@Repository
public interface GenericsDetailsRepository extends JpaRepository<GenericsDetails, Long> {

	//@Query(value = "select max(gdetail_id) from pm_gdetails", nativeQuery = true)
	@Query(value = "select PKG_PRIMEKEY.FNC_PRIMEKEY('pm_gdetails_seq') from dual", nativeQuery = true)
	Long lastIdValue();
	
	GenericsDetails findByGenericsDetailsNo(String genericsDetailsNo);
	
	List<GenericsDetails> findByGenericsDetailsIdIdAndRecordShowFlag(Long id, int showFlag);
	
	boolean existsByGenericsDetailsIdId(Long id);
	
	boolean existsByGenericsDetailsNo(String genericsDetailsNo);
	
	List<GenericsDetails> findByGenericsDetailsIdRowLanguageCodeAndRecordShowFlag(String language, int showFlag);
	
	List<GenericsDetails> findByRecordShowFlag(int showFlag);
	
}
